package org.allaymc.api.pdc;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;

/**
 * {@code PersistentDataTypeRegistry} associates primitive classes with adapters that encode,
 * recognize, and decode NBT values. Custom persistent types select adapters through their
 * primitive class, so several complex types can share storage while applying different
 * application-level conversion rules.
 *
 * @author IWareQ | Bukkit
 */
public interface PersistentDataTypeRegistry {
    /**
     * Creates and registers an adapter for {@code primitiveType}, replacing any previous mapping.
     * The supplied callbacks are retained for later conversions; {@code matcher} decides whether
     * a stored representation is compatible before extraction is attempted.
     */
    <T, Z> TagAdapter<T, Z> createAdapter(
            Class<T> primitiveType, Class<Z> nbtBaseType,
            BiFunction<PersistentDataType<T, ?>, T, Z> builder,
            BiFunction<PersistentDataType<T, ?>, Z, T> extractor,
            BiPredicate<PersistentDataType<T, ?>, Object> matcher
    );

    /**
     * Builds the stored representation using the adapter selected by {@code type}. The adapter
     * checks the primitive value's class before invoking its builder, and callback-specific
     * validation or aliasing rules still apply.
     *
     * @throws IllegalArgumentException if the primitive class is unregistered or the value has an incompatible class
     */
    <T> Object wrap(PersistentDataType<T, ?> type, final T value);

    /**
     * Returns whether the selected adapter recognizes {@code value}. This operation invokes the
     * adapter's matcher without decoding the complex application value, so a successful match
     * alone
     * does not prove custom converter validity.
     *
     * @throws IllegalArgumentException if the primitive class has no registered adapter
     */
    <T> boolean isInstanceOf(PersistentDataType<T, ?> type, Object value);

    /**
     * Returns the adapter registered for {@code type.getPrimitiveType()}. Selection uses the
     * primitive class rather than the complex class, and callers must not assume that unrelated
     * complex types receive distinct adapter instances.
     *
     * @throws IllegalArgumentException if the primitive class has no registered adapter
     */
    <T, Z> TagAdapter<T, Z> getAdapter(PersistentDataType<T, ?> type);

    /**
     * Decodes a stored representation through the adapter selected by {@code type}. The registry
     * validates the stored representation and extracted primitive class before the caller applies
     * its complex conversion. {@code null} representations or extractor results can fail during diagnostic
     * message construction instead of producing the validation exception.
     *
     * @throws IllegalArgumentException if the primitive adapter is absent or a non-{@code null} stored or extracted value has an incompatible type
     */
    <T, Z> T extract(PersistentDataType<T, ?> type, Z value);


    /**
     * {@code TagAdapter} binds one primitive class to its stored representation and conversion
     * callbacks. The constructor retains those callbacks, and the generated getters expose the
     * same class tokens and functions used during conversion.
     *
     * @param <P> primitive application representation
     * @param <T> stored NBT representation
     */
    @Getter
    @RequiredArgsConstructor
    class TagAdapter<P, T> {
        /**
         * Accepted primitive value class.
         */
        private final Class<P> primitiveType;
        /**
         * Expected stored representation class.
         */
        private final Class<T> nbtBaseType;
        /**
         * Primitive-to-storage conversion callback.
         */
        private final BiFunction<PersistentDataType<P, ?>, P, T> builder;
        /**
         * Storage-to-primitive conversion callback.
         */
        private final BiFunction<PersistentDataType<P, ?>, T, P> extractor;
        /**
         * Stored representation compatibility predicate.
         */
        private final BiPredicate<PersistentDataType<P, ?>, Object> matcher;

        /**
         * Checks the representation with {@code matcher} and extracts its primitive value. A
         * successful
         * matcher must also permit casting to {@code nbtBaseType}; the extractor's result is
         * returned
         * without a further primitive-class check here.
         *
         * @throws IllegalArgumentException if the matcher rejects a non-{@code null} representation
         */
        public P extract(PersistentDataType<P, ?> dataType, T value) {
            Preconditions.checkArgument(this.isInstance(dataType, value), "Invalid type: " + value.getClass().getSimpleName());
            return extractor.apply(dataType, this.nbtBaseType.cast(value));
        }

        /**
         * Builds the stored representation after checking {@code primitiveType}. A {@code null}
         * value
         * fails while the error message is constructed, and the builder controls the result's
         * representation and whether mutable data remains shared.
         *
         * @throws IllegalArgumentException if a non-{@code null} value has an incompatible primitive class
         */
        public Object build(PersistentDataType<P, ?> dataType, final P value) {
            Preconditions.checkArgument(this.primitiveType.isInstance(value), "Invalid value type: " + value.getClass().getSimpleName());
            return builder.apply(dataType, this.primitiveType.cast(value));
        }

        /**
         * Returns the compatibility decision supplied by this adapter's retained matcher.
         */
        public boolean isInstance(PersistentDataType<P, ?> dataType, Object value) {
            return this.matcher.test(dataType, value);
        }
    }
}
