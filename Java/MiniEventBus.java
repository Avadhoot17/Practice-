import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

/**
 * MiniEventBus - A lightweight publish/subscribe event bus with typed events.
 * Feature: Supports wildcard subscriptions, one-time listeners, and async dispatch.
 *          No external dependencies — pure Java.
 */
public class MiniEventBus {

    public record Event(String topic, Object payload, long timestamp) {
        public Event(String topic, Object payload) {
            this(topic, payload, System.currentTimeMillis());
        }
    }

    @FunctionalInterface
    public interface EventHandler {
        void handle(Event event);
    }

    private final Map<String, List<EventHandler>> handlers = new ConcurrentHashMap<>();
    private final List<EventHandler> wildcardHandlers = new CopyOnWriteArrayList<>();
    private final ExecutorService asyncExecutor = Executors.newCachedThreadPool();

    // ── Subscribe ─────────────────────────────────────────────────────────

    public Runnable on(String topic, EventHandler handler) {
        handlers.computeIfAbsent(topic, k -> new CopyOnWriteArrayList<>()).add(handler);
        return () -> off(topic, handler); // returns unsubscribe function
    }

    /** Fires only once, then auto-removes itself. */
    public void once(String topic, EventHandler handler) {
        Runnable[] unsub = new Runnable[1];
        EventHandler wrapper = e -> {
            handler.handle(e);
            unsub[0].run();
        };
        unsub[0] = on(topic, wrapper);
    }

    /** Receives every event regardless of topic. */
    public void onAll(EventHandler handler) {
        wildcardHandlers.add(handler);
    }

    public void off(String topic, EventHandler handler) {
        List<EventHandler> list = handlers.get(topic);
        if (list != null) list.remove(handler);
    }

    // ── Publish ───────────────────────────────────────────────────────────

    public void emit(String topic, Object payload) {
        Event event = new Event(topic, payload);
        List<EventHandler> topicHandlers = handlers.getOrDefault(topic, List.of());
        for (EventHandler h : topicHandlers) safeInvoke(h, event);
        for (EventHandler h : wildcardHandlers) safeInvoke(h, event);
    }

    /** Non-blocking: dispatches to listeners in a background thread. */
    public void emitAsync(String topic, Object payload) {
        asyncExecutor.submit(() -> emit(topic, payload));
    }

    private void safeInvoke(EventHandler h, Event e) {
        try { h.handle(e); }
        catch (Exception ex) {
            System.err.println("Handler error on [" + e.topic() + "]: " + ex.getMessage());
        }
    }

    public void shutdown() { asyncExecutor.shutdown(); }

    // ── Demo ──────────────────────────────────────────────────────────────
    public static void main(String[] args) throws InterruptedException {
        MiniEventBus bus = new MiniEventBus();

        // Wildcard listener — logs everything
        bus.onAll(e -> System.out.printf("[LOG] %-20s → %s%n", e.topic(), e.payload()));

        // Permanent listener
        Runnable unsub = bus.on("user.login", e ->
                System.out.println("Welcome back, " + e.payload() + "!"));

        // One-time listener
        bus.once("app.start", e ->
                System.out.println("App started at " + e.timestamp() + " (fires once)"));

        bus.emit("app.start", "v2.1.0");
        bus.emit("app.start", "v2.1.0"); // second emit — once listener won't fire again

        bus.emit("user.login", "Alice");
        bus.emit("user.login", "Bob");

        // Unsubscribe Alice/Bob login handler
        unsub.run();
        System.out.println("\n[Unsubscribed login handler]");
        bus.emit("user.login", "Carol"); // only wildcard fires

        // Async emit
        bus.emitAsync("order.placed", Map.of("id", 9901, "total", "$49.99"));
        Thread.sleep(100); // give async a moment

        bus.shutdown();
    }
}
