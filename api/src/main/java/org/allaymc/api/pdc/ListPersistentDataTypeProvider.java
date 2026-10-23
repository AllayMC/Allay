package org.allaymc.api.pdc;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * {@code ListPersistentDataTypeProvider} supplies reusable list adapters for built-in persistent
 * types and creates adapters for custom element types. Each adapter delegates element conversion
 * to its retained type; conversions return lazy views of the supplied lists, so later source-list
 * changes remain visible and mutable elements can remain shared.
 *
 * @author IWareQ | Bukkit
 */
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public final class ListPersistentDataTypeProvider {
    // The scalar adapters reuse built-in element types for each list conversion. BYTE, SHORT,
    // INTEGER, LONG, FLOAT, DOUBLE, BOOLEAN, and STRING remain shared across provider instances.
    /**
     * byte element list adapter.
     */
    private static final ListPersistentDataType<Byte, Byte> BYTE = new ListPersistentDataTypeImpl<>(PersistentDataType.BYTE);
    /**
     * short element list adapter.
     */
    private static final ListPersistentDataType<Short, Short> SHORT = new ListPersistentDataTypeImpl<>(PersistentDataType.SHORT);
    /**
     * integer element list adapter.
     */
    private static final ListPersistentDataType<Integer, Integer> INTEGER = new ListPersistentDataTypeImpl<>(PersistentDataType.INTEGER);
    /**
     * long element list adapter.
     */
    private static final ListPersistentDataType<Long, Long> LONG = new ListPersistentDataTypeImpl<>(PersistentDataType.LONG);
    /**
     * float element list adapter.
     */
    private static final ListPersistentDataType<Float, Float> FLOAT = new ListPersistentDataTypeImpl<>(PersistentDataType.FLOAT);
    /**
     * double element list adapter.
     */
    private static final ListPersistentDataType<Double, Double> DOUBLE = new ListPersistentDataTypeImpl<>(PersistentDataType.DOUBLE);
    /**
     * boolean element list adapter.
     */
    private static final ListPersistentDataType<Byte, Boolean> BOOLEAN = new ListPersistentDataTypeImpl<>(PersistentDataType.BOOLEAN);
    /**
     * string element list adapter.
     */
    private static final ListPersistentDataType<String, String> STRING = new ListPersistentDataTypeImpl<>(PersistentDataType.STRING);

    // BYTE_ARRAY, INTEGER_ARRAY, and LONG_ARRAY retain their primitive array adapters. Those
    // element adapters pass array references through conversion, so callers manage mutable data.
    /**
     * byte-array element list adapter.
     */
    private static final ListPersistentDataType<byte[], byte[]> BYTE_ARRAY = new ListPersistentDataTypeImpl<>(PersistentDataType.BYTE_ARRAY);
    /**
     * integer-array element list adapter.
     */
    private static final ListPersistentDataType<int[], int[]> INTEGER_ARRAY = new ListPersistentDataTypeImpl<>(PersistentDataType.INTEGER_ARRAY);
    /**
     * long-array element list adapter.
     */
    private static final ListPersistentDataType<long[], long[]> LONG_ARRAY = new ListPersistentDataTypeImpl<>(PersistentDataType.LONG_ARRAY);

    // DATA_CONTAINER uses the container adapter when a list becomes NBT, allowing each
    // element to supply its own compound representation through the same registry.
    /**
     * nested-container element list adapter.
     */
    private static final ListPersistentDataType<PersistentDataContainer, PersistentDataContainer> DATA_CONTAINER =
            new ListPersistentDataTypeImpl<>(PersistentDataType.TAG_CONTAINER);

    /**
     * Returns a shared {@link ListPersistentDataType} for storing lists of bytes.
     *
     * @return the persistent data type for lists of bytes
     */
    public ListPersistentDataType<Byte, Byte> bytes() {
        return BYTE;
    }

    /**
     * Returns a shared {@link ListPersistentDataType} for storing lists of shorts.
     *
     * @return the persistent data type for lists of shorts
     */
    public ListPersistentDataType<Short, Short> shorts() {
        return SHORT;
    }

    /**
     * Returns a shared {@link ListPersistentDataType} for storing lists of integers.
     *
     * @return the persistent data type for lists of integers
     */
    public ListPersistentDataType<Integer, Integer> integers() {
        return INTEGER;
    }

    /**
     * Returns a shared {@link ListPersistentDataType} for storing lists of longs.
     *
     * @return the persistent data type for lists of longs
     */
    public ListPersistentDataType<Long, Long> longs() {
        return LONG;
    }

    /**
     * Returns a shared {@link ListPersistentDataType} for storing lists of floats.
     *
     * @return the persistent data type for lists of floats
     */
    public ListPersistentDataType<Float, Float> floats() {
        return FLOAT;
    }

    /**
     * Returns a shared {@link ListPersistentDataType} for storing lists of doubles.
     *
     * @return the persistent data type for lists of doubles
     */
    public ListPersistentDataType<Double, Double> doubles() {
        return DOUBLE;
    }

    /**
     * Returns a shared {@link ListPersistentDataType} for storing lists of booleans.
     *
     * @return the persistent data type for lists of booleans
     */
    public ListPersistentDataType<Byte, Boolean> booleans() {
        return BOOLEAN;
    }

    /**
     * Returns a shared {@link ListPersistentDataType} for storing lists of strings.
     *
     * @return the persistent data type for lists of strings
     */
    public ListPersistentDataType<String, String> strings() {
        return STRING;
    }

    /**
     * Returns a shared {@link ListPersistentDataType} for storing lists of byte arrays.
     *
     * @return the persistent data type for lists of byte arrays
     */
    public ListPersistentDataType<byte[], byte[]> byteArrays() {
        return BYTE_ARRAY;
    }

    /**
     * Returns a shared {@link ListPersistentDataType} for storing lists of int arrays.
     *
     * @return the persistent data type for lists of int arrays
     */
    public ListPersistentDataType<int[], int[]> integerArrays() {
        return INTEGER_ARRAY;
    }

    /**
     * Returns a shared {@link ListPersistentDataType} for storing lists of long arrays.
     *
     * @return the persistent data type for lists of long arrays
     */
    public ListPersistentDataType<long[], long[]> longArrays() {
        return LONG_ARRAY;
    }

    /**
     * Returns a shared {@link ListPersistentDataType} for storing lists of persistent data containers.
     *
     * @return the persistent data type for lists of persistent data containers
     */
    public ListPersistentDataType<PersistentDataContainer, PersistentDataContainer> dataContainers() {
        return DATA_CONTAINER;
    }

    /**
     * Creates a list adapter retaining the supplied element converter. The converter is used
     * lazily
     * when values are accessed in converted lists, and the registry requires a supported primitive
     * element type when the list is stored.
     */
    public <P, C> ListPersistentDataType<P, C> listTypeFrom(PersistentDataType<P, C> elementType) {
        return new ListPersistentDataTypeImpl<>(elementType);
    }

    /**
     * {@code ListPersistentDataTypeImpl} converts list elements through one retained persistent
     * data
     * type. {@link Lists#transform(List, com.google.common.base.Function)} supplies lazy views
     * rather
     * than independent copies of the source lists.
     *
     * @param <P> primitive element representation
     * @param <C> converted element representation
     */
    @SuppressWarnings("unchecked")
    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    private static final class ListPersistentDataTypeImpl<P, C> implements ListPersistentDataType<P, C> {
        /**
         * Retained element conversion adapter.
         */
        private final PersistentDataType<P, C> innerType;

        /**
         * Returns the erased list class used for registry adapter lookup.
         */
        @Override
        public Class<List<P>> getPrimitiveType() {
            return (Class<List<P>>) (Object) List.class;
        }

        /**
         * Returns the erased list class exposed for converted values.
         */
        @Override
        public Class<List<C>> getComplexType() {
            return (Class<List<C>>) (Object) List.class;
        }

        /**
         * Returns a lazy primitive view backed by {@code complex}. Source-list changes remain
         * visible,
         * and each access invokes {@code innerType.toPrimitive}; callers cannot assume an
         * independent
         * snapshot or independent mutable elements.
         */
        @Override
        public List<P> toPrimitive(List<C> complex, PersistentDataAdapterContext context) {
            return Lists.transform(complex, s -> innerType.toPrimitive(s, context));
        }

        /**
         * Returns a lazy converted view backed by {@code primitive}. Source-list changes remain
         * visible,
         * and each access invokes {@code innerType.fromPrimitive}; callers cannot assume an
         * independent
         * snapshot or independent mutable elements.
         */
        @Override
        public List<C> fromPrimitive(List<P> primitive, PersistentDataAdapterContext context) {
            return Lists.transform(primitive, s -> innerType.fromPrimitive(s, context));
        }

        /**
         * Returns the retained element adapter used by both conversion directions.
         */
        @Override
        public PersistentDataType<P, C> getElementType() {
            return this.innerType;
        }
    }
}
