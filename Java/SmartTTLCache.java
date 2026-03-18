import java.util.concurrent.*;
import java.util.*;

/**
 * SmartTTLCache - A thread-safe generic cache with automatic Time-To-Live (TTL) expiry.
 * Feature: Entries automatically expire and are evicted after a configurable duration,
 *          with background cleanup and hit/miss statistics tracking.
 */
public class SmartTTLCache<K, V> {

    private static class CacheEntry<V> {
        final V value;
        final long expiryTime;

        CacheEntry(V value, long ttlMillis) {
            this.value = value;
            this.expiryTime = System.currentTimeMillis() + ttlMillis;
        }

        boolean isExpired() {
            return System.currentTimeMillis() > expiryTime;
        }
    }

    private final ConcurrentHashMap<K, CacheEntry<V>> store = new ConcurrentHashMap<>();
    private final long defaultTtlMillis;
    private final ScheduledExecutorService cleaner = Executors.newSingleThreadScheduledExecutor();

    private long hits = 0;
    private long misses = 0;

    public SmartTTLCache(long defaultTtlMillis, long cleanupIntervalMillis) {
        this.defaultTtlMillis = defaultTtlMillis;
        cleaner.scheduleAtFixedRate(this::evictExpired, cleanupIntervalMillis,
                cleanupIntervalMillis, TimeUnit.MILLISECONDS);
    }

    public void put(K key, V value) {
        store.put(key, new CacheEntry<>(value, defaultTtlMillis));
    }

    public void put(K key, V value, long customTtlMillis) {
        store.put(key, new CacheEntry<>(value, customTtlMillis));
    }

    public Optional<V> get(K key) {
        CacheEntry<V> entry = store.get(key);
        if (entry == null || entry.isExpired()) {
            if (entry != null) store.remove(key);
            misses++;
            return Optional.empty();
        }
        hits++;
        return Optional.of(entry.value);
    }

    public void invalidate(K key) {
        store.remove(key);
    }

    private void evictExpired() {
        store.entrySet().removeIf(e -> e.getValue().isExpired());
    }

    public void printStats() {
        long total = hits + misses;
        double hitRate = total == 0 ? 0 : (hits * 100.0 / total);
        System.out.printf("Cache Stats — Size: %d | Hits: %d | Misses: %d | Hit Rate: %.1f%%%n",
                store.size(), hits, misses, hitRate);
    }

    public void shutdown() {
        cleaner.shutdown();
    }

    // ── Demo ──────────────────────────────────────────────────────────────
    public static void main(String[] args) throws InterruptedException {
        // TTL = 2 seconds, cleanup every 1 second
        SmartTTLCache<String, String> cache = new SmartTTLCache<>(2000, 1000);

        cache.put("user:101", "Alice");
        cache.put("user:102", "Bob", 500); // expires in 0.5s
        cache.put("session:abc", "active");

        System.out.println("user:101  → " + cache.get("user:101"));   // Alice
        System.out.println("user:102  → " + cache.get("user:102"));   // Bob

        Thread.sleep(600); // let Bob's entry expire
        System.out.println("\nAfter 600ms:");
        System.out.println("user:102  → " + cache.get("user:102"));   // empty

        Thread.sleep(1600); // let remaining entries expire
        System.out.println("\nAfter 2.2s total:");
        System.out.println("user:101  → " + cache.get("user:101"));   // empty
        System.out.println("session   → " + cache.get("session:abc")); // empty

        cache.printStats();
        cache.shutdown();
    }
}
