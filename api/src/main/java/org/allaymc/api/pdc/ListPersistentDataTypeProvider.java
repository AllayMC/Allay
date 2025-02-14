package org.allaymc.api.pdc;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Provides shared instances of {@link ListPersistentDataType} for the known primitive types supported by {@link PersistentDataType}.
 *
 * @author IWareQ | Bukkit
 */
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public final class ListPersistentDataTypeProvider {
    private static final ListPersistentDataType<Byte, Byte> BYTE = new ListPersistentDataTypeImpl<>(PersistentDataType.BYTE);
    private static final ListPersistentDataType<Short, Short> SHORT = new ListPersistentDataTypeImpl<>(PersistentDataType.SHORT);
    private static final ListPersistentDataType<Integer, Integer> INTEGER = new ListPersistentDataTypeImpl<>(PersistentDataType.INTEGER);
    private static final ListPersistentDataType<Long, Long> LONG = new ListPersistentDataTypeImpl<>(PersistentDataType.LONG);
    private static final ListPersistentDataType<Float, Float> FLOAT = new ListPersistentDataTypeImpl<>(PersistentDataType.FLOAT);
    private static final ListPersistentDataType<Double, Double> DOUBLE = new ListPersistentDataTypeImpl<>(PersistentDataType.DOUBLE);
    private static final ListPersistentDataType<Byte, Boolean> BOOLEAN = new ListPersistentDataTypeImpl<>(PersistentDataType.BOOLEAN);
    private static final ListPersistentDataType<String, String> STRING = new ListPersistentDataTypeImpl<>(PersistentDataType.STRING);

    private static final ListPersistentDataType<byte[], byte[]> BYTE_ARRAY = new ListPersistentDataTypeImpl<>(PersistentDataType.BYTE_ARRAY);
    private static final ListPersistentDataType<int[], int[]> INTEGER_ARRAY = new ListPersistentDataTypeImpl<>(PersistentDataType.INTEGER_ARRAY);
    private static final ListPersistentDataType<long[], long[]> LONG_ARRAY = new ListPersistentDataTypeImpl<>(PersistentDataType.LONG_ARRAY);

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
     * Creates a new {@link ListPersistentDataType} for a given element type.
     *
     * @param elementType the persistent data type for list elements
     * @param <P>         the primitive type of the list elements
     * @param <C>         the complex type of the list elements
     *
     * @return a new list persistent data type for the given element type
     */
    public <P, C> ListPersistentDataType<P, C> listTypeFrom(PersistentDataType<P, C> elementType) {
        return new ListPersistentDataTypeImpl<>(elementType);
    }

    /**
     * Private implementation of {@link ListPersistentDataType} that
     * converts between primitive and complex list types using {@link Collections2}.
     *
     * @param <P> the primitive type of the list elements
     * @param <C> the complex type of the list elements
     */
    @SuppressWarnings("unchecked")
    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    private static final class ListPersistentDataTypeImpl<P, C> implements ListPersistentDataType<P, C> {
        private final PersistentDataType<P, C> innerType;

        @Override
        public Class<List<P>> getPrimitiveType() {
            return (Class<List<P>>) (Object) List.class;
        }

        @Override
        public Class<List<C>> getComplexType() {
            return (Class<List<C>>) (Object) List.class;
        }

        @Override
        public List<P> toPrimitive(List<C> complex, PersistentDataAdapterContext context) {
            return Lists.transform(complex, s -> innerType.toPrimitive(s, context));
        }

        @Override
        public List<C> fromPrimitive(List<P> primitive, PersistentDataAdapterContext context) {
            return Lists.transform(primitive, s -> innerType.fromPrimitive(s, context));
        }

        @Override
        public PersistentDataType<P, C> getElementType() {
            return this.innerType;
        }
    }
}
