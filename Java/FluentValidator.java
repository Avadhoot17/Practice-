import java.util.*;
import java.util.function.*;
import java.util.regex.*;

/**
 * FluentValidator - A reusable, chainable validation pipeline builder.
 * Feature: Build type-safe validators by chaining rules in a fluent API.
 *          Collects ALL errors at once (not fail-fast) so users see everything wrong.
 */
public class FluentValidator<T> {

    private final T value;
    private final String fieldName;
    private final List<String> errors = new ArrayList<>();

    private FluentValidator(T value, String fieldName) {
        this.value = value;
        this.fieldName = fieldName;
    }

    public static <T> FluentValidator<T> of(T value, String fieldName) {
        return new FluentValidator<>(value, fieldName);
    }

    public FluentValidator<T> mustSatisfy(Predicate<T> rule, String errorMsg) {
        if (value == null || !rule.test(value)) {
            errors.add("[" + fieldName + "] " + errorMsg);
        }
        return this;
    }

    public FluentValidator<T> notNull() {
        if (value == null) errors.add("[" + fieldName + "] must not be null");
        return this;
    }

    // String-specific rules (safe-cast shortcut)
    public FluentValidator<T> minLength(int min) {
        if (value instanceof String s && s.length() < min)
            errors.add("[" + fieldName + "] must be at least " + min + " characters");
        return this;
    }

    public FluentValidator<T> maxLength(int max) {
        if (value instanceof String s && s.length() > max)
            errors.add("[" + fieldName + "] must be at most " + max + " characters");
        return this;
    }

    public FluentValidator<T> matchesRegex(String regex, String hint) {
        if (value instanceof String s && !Pattern.matches(regex, s))
            errors.add("[" + fieldName + "] " + hint);
        return this;
    }

    // Number-specific rules
    public FluentValidator<T> min(double min) {
        if (value instanceof Number n && n.doubleValue() < min)
            errors.add("[" + fieldName + "] must be >= " + min);
        return this;
    }

    public FluentValidator<T> max(double max) {
        if (value instanceof Number n && n.doubleValue() > max)
            errors.add("[" + fieldName + "] must be <= " + max);
        return this;
    }

    public ValidationResult validate() {
        return new ValidationResult(new ArrayList<>(errors));
    }

    // ── Result ────────────────────────────────────────────────────────────
    public static class ValidationResult {
        private final List<String> errors;

        ValidationResult(List<String> errors) {
            this.errors = errors;
        }

        public boolean isValid() { return errors.isEmpty(); }
        public List<String> getErrors() { return Collections.unmodifiableList(errors); }

        public void throwIfInvalid() {
            if (!isValid()) throw new IllegalArgumentException(
                    "Validation failed:\n  • " + String.join("\n  • ", errors));
        }

        @Override public String toString() {
            return isValid() ? "VALID" : "INVALID:\n  • " + String.join("\n  • ", errors);
        }
    }

    // ── Composite helper ──────────────────────────────────────────────────
    public static ValidationResult validateAll(FluentValidator<?>... validators) {
        List<String> all = new ArrayList<>();
        for (FluentValidator<?> v : validators) all.addAll(v.errors);
        return new ValidationResult(all);
    }

    // ── Demo ──────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        System.out.println("=== Valid user ===");
        var result1 = validateAll(
            FluentValidator.of("alice_dev", "username")
                .notNull().minLength(3).maxLength(20)
                .matchesRegex("[a-z0-9_]+", "only lowercase letters, digits, underscores"),
            FluentValidator.of("alice@example.com", "email")
                .notNull().matchesRegex(".+@.+\\..+", "invalid email format"),
            FluentValidator.of(25, "age")
                .notNull().<Integer>mustSatisfy(a -> a >= 18, "must be 18+").min(0).max(120)
        );
        System.out.println(result1);

        System.out.println("\n=== Invalid user ===");
        var result2 = validateAll(
            FluentValidator.of("AL", "username")
                .notNull().minLength(3).maxLength(20)
                .matchesRegex("[a-z0-9_]+", "only lowercase letters, digits, underscores"),
            FluentValidator.of("not-an-email", "email")
                .notNull().matchesRegex(".+@.+\\..+", "invalid email format"),
            FluentValidator.of(15, "age")
                .notNull().<Integer>mustSatisfy(a -> a >= 18, "must be 18+").min(0).max(120)
        );
        System.out.println(result2);
    }
}
