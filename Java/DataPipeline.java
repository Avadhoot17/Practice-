import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * DataPipeline - A type-safe, immutable data transformation pipeline.
 * Feature: Chain map/filter/reduce steps declaratively. Each step is named,
 *          making it easy to inspect, debug, and replay transformations.
 *          The pipeline itself is immutable — branching creates new pipelines.
 */
public class DataPipeline<T> {

    private final List<T> data;
    private final List<String> stepHistory;

    private DataPipeline(List<T> data, List<String> history) {
        this.data = Collections.unmodifiableList(data);
        this.stepHistory = Collections.unmodifiableList(history);
    }

    // ── Factory ───────────────────────────────────────────────────────────

    public static <T> DataPipeline<T> from(List<T> source) {
        return new DataPipeline<>(new ArrayList<>(source), List.of("source(" + source.size() + " items)"));
    }

    @SafeVarargs
    public static <T> DataPipeline<T> of(T... items) {
        return from(Arrays.asList(items));
    }

    // ── Transformations (each returns a NEW pipeline) ─────────────────────

    public DataPipeline<T> filter(String name, Predicate<T> predicate) {
        List<T> result = data.stream().filter(predicate).collect(Collectors.toList());
        return log("filter[" + name + "] " + data.size() + "→" + result.size(), result);
    }

    public <R> DataPipeline<R> map(String name, Function<T, R> mapper) {
        List<R> result = data.stream().map(mapper).collect(Collectors.toList());
        return log("map[" + name + "]", result);
    }

    public DataPipeline<T> sorted(String name, Comparator<T> comparator) {
        List<T> result = data.stream().sorted(comparator).collect(Collectors.toList());
        return log("sort[" + name + "]", result);
    }

    public DataPipeline<T> limit(int n) {
        List<T> result = data.stream().limit(n).collect(Collectors.toList());
        return log("limit(" + n + ")", result);
    }

    public DataPipeline<T> distinct(String name) {
        List<T> result = data.stream().distinct().collect(Collectors.toList());
        return log("distinct[" + name + "] " + data.size() + "→" + result.size(), result);
    }

    public DataPipeline<T> peek(String name, Consumer<T> action) {
        data.forEach(action);
        return log("peek[" + name + "]", data);
    }

    // ── Terminal operations ───────────────────────────────────────────────

    public <R> R reduce(R identity, BiFunction<R, T, R> accumulator) {
        R result = identity;
        for (T item : data) result = accumulator.apply(result, item);
        return result;
    }

    public List<T> toList() { return new ArrayList<>(data); }

    public <K> Map<K, List<T>> groupBy(Function<T, K> classifier) {
        return data.stream().collect(Collectors.groupingBy(classifier));
    }

    public Optional<T> first() { return data.stream().findFirst(); }

    public int count() { return data.size(); }

    // ── Introspection ─────────────────────────────────────────────────────

    public void printHistory() {
        System.out.println("Pipeline trace:");
        for (int i = 0; i < stepHistory.size(); i++)
            System.out.println("  " + i + ". " + stepHistory.get(i));
    }

    public void printData() {
        System.out.println("Data (" + data.size() + "): " + data);
    }

    // ── Internal ──────────────────────────────────────────────────────────

    private <R> DataPipeline<R> log(String step, List<R> newData) {
        List<String> newHistory = new ArrayList<>(stepHistory);
        newHistory.add(step);
        return new DataPipeline<>(newData, newHistory);
    }

    // ── Demo ──────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        record Product(String name, String category, double price, int stock) {}

        List<Product> products = List.of(
            new Product("Laptop",   "Electronics", 999.99, 15),
            new Product("Phone",    "Electronics", 699.00, 42),
            new Product("Desk",     "Furniture",   349.50,  8),
            new Product("Chair",    "Furniture",   219.99, 23),
            new Product("Monitor",  "Electronics", 529.00,  5),
            new Product("Lamp",     "Furniture",    49.99, 60),
            new Product("Keyboard", "Electronics",  89.99, 30),
            new Product("Tablet",   "Electronics", 449.00,  0)
        );

        System.out.println("=== Top 3 in-stock Electronics under $800 ===");
        var topElectronics = DataPipeline.from(products)
            .filter("in-stock",    p -> p.stock() > 0)
            .filter("electronics", p -> p.category().equals("Electronics"))
            .filter("under-$800",  p -> p.price() < 800)
            .sorted("by-price",    Comparator.comparingDouble(Product::price).reversed())
            .limit(3)
            .map("name+price",     p -> p.name() + " ($" + p.price() + ")");

        topElectronics.printData();
        topElectronics.printHistory();

        System.out.println("\n=== Total value of in-stock inventory ===");
        double totalValue = DataPipeline.from(products)
            .filter("in-stock", p -> p.stock() > 0)
            .reduce(0.0, (sum, p) -> sum + p.price() * p.stock());
        System.out.printf("Total inventory value: $%.2f%n", totalValue);

        System.out.println("\n=== Products grouped by category ===");
        var byCategory = DataPipeline.from(products)
            .groupBy(Product::category);
        byCategory.forEach((cat, list) ->
            System.out.println("  " + cat + ": " + list.stream().map(Product::name).toList()));
    }
}
