package org.allaymc.api.pdc;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;

/**
 * @author IWareQ | Bukkit
 */
public interface PersistentDataTypeRegistry {
    /**
     * Creates a tag adapter that maps a primitive type to a tag representation with additional type matching validation.
     *
     * @param <T>           the type of the primitive value
     * @param <Z>           the type of the tag representation
     * @param primitiveType the class type of the primitive value
     * @param nbtBaseType   the class type of the tag representation
     * @param builder       a function that converts a primitive value to its tag representation
     * @param extractor     a function that converts a tag representation back to the primitive value
     * @param matcher       a function that validates whether a tag value matches the expected type
     *
     * @return a tag adapter that can perform conversions between the primitive type and tag representation
     * with additional validation through the matcher function
     *
     * @throws IllegalArgumentException if no suitable tag type adapter is found for the specified types or if the matcher fails
     */
    <T, Z> TagAdapter<T, Z> createAdapter(
            Class<T> primitiveType, Class<Z> nbtBaseType,
            BiFunction<PersistentDataType<T, ?>, T, Z> builder,
            BiFunction<PersistentDataType<T, ?>, Z, T> extractor,
            BiPredicate<PersistentDataType<T, ?>, Object> matcher
    );

    /**
     * Wraps the given primitive value into a tag instance.
     *
     * @param type  the persistent data type of the value
     * @param value the value to be stored
     * @param <T>   the generic type of the value
     *
     * @return the wrapped tag instance
     *
     * @throws IllegalArgumentException if no suitable tag type adapter is found for this type.
     */
    <T> Object wrap(PersistentDataType<T, ?> type, final T value);

    /**
     * Checks if the given tag value instance matches the specified primitive type.
     *
     * @param type  the persistent data type of the value
     * @param value the value instance to check
     * @param <T>   the generic type of the persistent data type
     *
     * @return {@code true} if the tag value is an instance of the primitive type; {@code false} otherwise
     *
     * @throws IllegalArgumentException if no suitable tag type adapter is found for this type.
     */
    <T> boolean isInstanceOf(PersistentDataType<T, ?> type, Object value);

    /**
     * Retrieves the adapter associated with the specified persistent data type.
     *
     * @param type the persistent data type
     * @param <T>  the generic type of the primitive value
     * @param <Z>  the generic type of the tag representation
     *
     * @return the corresponding tag adapter instance
     *
     * @throws IllegalArgumentException if no adapter is found for the specified persistent data type.
     */
    <T, Z> TagAdapter<T, Z> getAdapter(PersistentDataType<T, ?> type);

    /**
     * Extracts the stored primitive value from the given tag representation.
     *
     * @param type  the persistent data type
     * @param value the tag representation containing the stored value
     * @param <T>   the generic type of the extracted value
     * @param <Z>   the generic type of the tag representation
     *
     * @return the extracted primitive value
     *
     * @throws IllegalArgumentException if the tag representation doesn't match the expected type or if no suitable adapter is found.
     */
    <T, Z> T extract(PersistentDataType<T, ?> type, Z value);


    /**
     * A tag adapter maps a primitive type to a specific tag representation used for persistence.
     *
     * @param <P> the primitive type expected by the persistent data type
     * @param <T> the tag representation type
     */
    @Getter
    @RequiredArgsConstructor
    class TagAdapter<P, T> {
        private final Class<P> primitiveType;
        private final Class<T> nbtBaseType;
        private final BiFunction<PersistentDataType<P, ?>, P, T> builder;
        private final BiFunction<PersistentDataType<P, ?>, T, P> extractor;
        private final BiPredicate<PersistentDataType<P, ?>, Object> matcher;

        /**
         * Extracts the primitive value from the provided tag representation.
         *
         * @param dataType the persistent data type
         * @param value    the tag representation
         *
         * @return the extracted primitive value
         *
         * @throws IllegalArgumentException if the provided tag representation is invalid.
         */
        public P extract(PersistentDataType<P, ?> dataType, T value) {
            Preconditions.checkArgument(this.isInstance(dataType, value), "Invalid type: " + value.getClass().getSimpleName());
            return extractor.apply(dataType, this.nbtBaseType.cast(value));
        }

        /**
         * Builds a tag representation from the provided primitive value.
         *
         * @param dataType the persistent data type
         * @param value    the primitive value to be wrapped
         *
         * @return the constructed tag representation
         *
         * @throws IllegalArgumentException if the provided value is of an invalid type.
         */
        public Object build(PersistentDataType<P, ?> dataType, final P value) {
            Preconditions.checkArgument(this.primitiveType.isInstance(value), "Invalid value type: " + value.getClass().getSimpleName());
            return builder.apply(dataType, this.primitiveType.cast(value));
        }

        /**
         * Determines whether the provided tag representation is an instance of the expected type.
         *
         * @param dataType the persistent data type
         * @param value    the tag representation to check
         *
         * @return {@code true} if the tag representation matches the expected type; {@code false} otherwise
         */
        public boolean isInstance(PersistentDataType<P, ?> dataType, Object value) {
            return this.matcher.test(dataType, value);
        }
    }
}
