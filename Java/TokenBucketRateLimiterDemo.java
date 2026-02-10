import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TokenBucketRateLimiter {

    private final long capacity;
    private final long refillRate;

    private double currentTokens;
    private long lastRefillTimestamp;

    public TokenBucketRateLimiter(long capacity, long refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.currentTokens = capacity;
        this.lastRefillTimestamp = System.nanoTime();
    }

    private void refill() {
        long now = System.nanoTime();
        double tokensToAdd =
                ((now - lastRefillTimestamp) / 1_000_000_000.0) * refillRate;

        if (tokensToAdd > 0) {
            currentTokens = Math.min(capacity, currentTokens + tokensToAdd);
            lastRefillTimestamp = now;
        }
    }

    public synchronized boolean allowRequest() {
        refill();

        if (currentTokens >= 1) {
            currentTokens -= 1;
            return true;
        }
        return false;
    }

    // -------- MAIN METHOD ADDED HERE --------
    public static void main(String[] args) {

        TokenBucketRateLimiter limiter = new TokenBucketRateLimiter(10, 5);

        ExecutorService pool = Executors.newFixedThreadPool(5);

        for (int i = 1; i <= 20; i++) {
            int requestId = i;

            pool.submit(() -> {
                if (limiter.allowRequest()) {
                    System.out.println("Request " + requestId + " allowed");
                } else {
                    System.out.println("Request " + requestId + " blocked");
                }
            });

            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {}
        }

        pool.shutdown();
    }
}
