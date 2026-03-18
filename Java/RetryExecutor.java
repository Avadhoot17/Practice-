import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

/**
 * RetryExecutor - Executes tasks with configurable retry logic and exponential backoff.
 * Feature: Supports jitter, max-delay capping, custom retry conditions,
 *          and a detailed execution report after all attempts.
 */
public class RetryExecutor {

    public record RetryConfig(
        int maxAttempts,
        long initialDelayMs,
        double backoffMultiplier,
        long maxDelayMs,
        boolean useJitter,
        Predicate<Exception> retryOn
    ) {
        public static RetryConfig defaults() {
            return new RetryConfig(3, 200, 2.0, 5000, true, ex -> true);
        }

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private int maxAttempts = 3;
            private long initialDelayMs = 200;
            private double backoffMultiplier = 2.0;
            private long maxDelayMs = 5000;
            private boolean useJitter = true;
            private Predicate<Exception> retryOn = ex -> true;

            public Builder maxAttempts(int v)           { maxAttempts = v; return this; }
            public Builder initialDelayMs(long v)       { initialDelayMs = v; return this; }
            public Builder backoffMultiplier(double v)  { backoffMultiplier = v; return this; }
            public Builder maxDelayMs(long v)           { maxDelayMs = v; return this; }
            public Builder useJitter(boolean v)         { useJitter = v; return this; }
            public Builder retryOn(Predicate<Exception> v) { retryOn = v; return this; }
            public RetryConfig build() {
                return new RetryConfig(maxAttempts, initialDelayMs, backoffMultiplier,
                        maxDelayMs, useJitter, retryOn);
            }
        }
    }

    public record AttemptLog(int attempt, long delayMs, String outcome, String error) {}

    public record ExecutionReport<T>(T result, List<AttemptLog> log, boolean succeeded) {
        public void print() {
            System.out.println("── Execution Report ──────────────────────────");
            for (AttemptLog l : log)
                System.out.printf("  Attempt %d | wait %4dms | %-8s | %s%n",
                        l.attempt(), l.delayMs(), l.outcome(), l.error() == null ? "—" : l.error());
            System.out.println("  Result: " + (succeeded ? "SUCCESS → " + result : "FAILED"));
            System.out.println("──────────────────────────────────────────────");
        }
    }

    private final RetryConfig config;
    private final Random rng = new Random();

    public RetryExecutor(RetryConfig config) {
        this.config = config;
    }

    public <T> ExecutionReport<T> execute(Callable<T> task) {
        List<AttemptLog> log = new ArrayList<>();
        long delay = config.initialDelayMs();

        for (int attempt = 1; attempt <= config.maxAttempts(); attempt++) {
            long waitMs = attempt == 1 ? 0 : delay;
            try {
                if (waitMs > 0) Thread.sleep(waitMs);
                T result = task.call();
                log.add(new AttemptLog(attempt, waitMs, "SUCCESS", null));
                return new ExecutionReport<>(result, log, true);
            } catch (Exception ex) {
                boolean willRetry = attempt < config.maxAttempts() && config.retryOn().test(ex);
                log.add(new AttemptLog(attempt, waitMs,
                        willRetry ? "RETRY" : "FAIL",
                        ex.getClass().getSimpleName() + ": " + ex.getMessage()));

                if (!willRetry) break;

                // Calculate next delay with optional jitter
                long next = (long) (delay * config.backoffMultiplier());
                next = Math.min(next, config.maxDelayMs());
                if (config.useJitter()) next = (long) (next * (0.5 + rng.nextDouble() * 0.5));
                delay = next;
            }
        }
        return new ExecutionReport<>(null, log, false);
    }

    // ── Demo ──────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        RetryExecutor executor = new RetryExecutor(
            RetryConfig.builder()
                .maxAttempts(4)
                .initialDelayMs(100)
                .backoffMultiplier(2.0)
                .maxDelayMs(800)
                .useJitter(false) // deterministic for demo
                .retryOn(ex -> ex instanceof RuntimeException)
                .build()
        );

        // Simulate a flaky service that succeeds on the 3rd try
        int[] attempt = {0};
        ExecutionReport<String> report = executor.execute(() -> {
            attempt[0]++;
            if (attempt[0] < 3) throw new RuntimeException("Connection timeout");
            return "{ \"status\": \"ok\", \"data\": 42 }";
        });

        report.print();

        // Simulate a task that always fails
        System.out.println();
        int[] a2 = {0};
        ExecutionReport<String> report2 = executor.execute(() -> {
            a2[0]++;
            throw new IllegalStateException("Service unavailable");
        });
        report2.print();
    }
}
