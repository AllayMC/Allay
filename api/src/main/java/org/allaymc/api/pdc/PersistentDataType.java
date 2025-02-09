package org.allaymc.api.pdc;

import lombok.RequiredArgsConstructor;

/**
 * Represents a data type used for persistent tags, allowing for serialization and deserialization between
 * primitive types and their corresponding complex object representations.
 *
 * <p>
 * This interface can be used to create custom {@link PersistentDataType}s for complex types.
 * For example, the {@code UUIDTagType} would implement this interface to handle UUID serialization:
 * </p>
 *
 * <pre>
 * {@code
 * public class UUIDTagType implements PersistentDataType<byte[], UUID> {
 *     @Override
 *     public Class<byte[]> getPrimitiveType() {
 *         return byte[].class;
 *     }
 *
 *     @Override
 *     public Class<UUID> getComplexType() {
 *         return UUID.class;
 *     }
 *
 *     @Override
 *     public byte[] toPrimitive(UUID complex, PersistentDataAdapterContext context) {
 *         var buffer = ByteBuffer.wrap(new byte[16]);
 *         buffer.putLong(complex.getMostSignificantBits());
 *         buffer.putLong(complex.getLeastSignificantBits());
 *         return buffer.array();
 *     }
 *
 *     @Override
 *     public UUID fromPrimitive(byte[] primitive, PersistentDataAdapterContext context) {
 *         var buffer = ByteBuffer.wrap(primitive);
 *         var mostSigBits = buffer.getLong();
 *         var leastSigBits = buffer.getLong();
 *         return new UUID(mostSigBits, leastSigBits);
 *     }
 * }
 * }
 * </pre>
 *
 * <p>
 * Custom implementations of this interface must define one of the existing primitive types.
 * Specifically, {@link #BOOLEAN} is not a primitive type, but rather a convenience implementation for boolean values.
 * </p>
 *
 * @param <P> the primitive type stored in the tag
 * @param <C> the complex object type derived from the primitive
 *
 * @author IWareQ | Bukkit
 */
public interface PersistentDataType<P, C> {

    /* Primitive types */
    PersistentDataType<Byte, Byte> BYTE = new Primitive<>(Byte.class);
    PersistentDataType<Short, Short> SHORT = new Primitive<>(Short.class);
    PersistentDataType<Integer, Integer> INTEGER = new Primitive<>(Integer.class);
    PersistentDataType<Long, Long> LONG = new Primitive<>(Long.class);
    PersistentDataType<Float, Float> FLOAT = new Primitive<>(Float.class);
    PersistentDataType<Double, Double> DOUBLE = new Primitive<>(Double.class);

    /**
     * A convenience implementation to handle conversion between Byte and Boolean.
     * Any byte value not equal to 0 is considered true.
     */
    PersistentDataType<Byte, java.lang.Boolean> BOOLEAN = new Boolean();

    PersistentDataType<String, String> STRING = new Primitive<>(String.class);

    /* Arrays of primitives */
    PersistentDataType<byte[], byte[]> BYTE_ARRAY = new Primitive<>(byte[].class);
    PersistentDataType<int[], int[]> INTEGER_ARRAY = new Primitive<>(int[].class);
    PersistentDataType<long[], long[]> LONG_ARRAY = new Primitive<>(long[].class);

    PersistentDataType<PersistentDataContainer, PersistentDataContainer> TAG_CONTAINER = new Primitive<>(PersistentDataContainer.class);

    /**
     * A data type provider that handles lists of other data types.
     *
     * <p>
     * Lists can't be directly used as primitive types
     * but can be stored through a {@link ListPersistentDataTypeProvider}.
     * </p>
     *
     * @see ListPersistentDataTypeProvider
     */
    ListPersistentDataTypeProvider LIST = new ListPersistentDataTypeProvider();

    /**
     * Returns the class representing the primitive type of this tag.
     *
     * @return the class of the primitive type
     */
    Class<P> getPrimitiveType();

    /**
     * Returns the class representing the complex object type corresponding to the primitive type.
     *
     * @return the class of the complex type
     */
    Class<C> getComplexType();

    /**
     * Converts a complex object into its primitive representation.
     *
     * @param complex the complex object to convert
     * @param context the context of the conversion
     *
     * @return the primitive representation of the complex object
     */
    P toPrimitive(C complex, PersistentDataAdapterContext context);

    /**
     * Converts a primitive value into its corresponding complex object.
     *
     * @param primitive the primitive value to convert
     * @param context   the context of the conversion
     *
     * @return the complex object corresponding to the primitive value
     */
    C fromPrimitive(P primitive, PersistentDataAdapterContext context);

    /**
     * A default implementation of {@link PersistentDataType} for primitive types where no conversion is needed.
     * The value is passed as is without transformation.
     *
     * @param <P> the type of the primitive object
     */
    @RequiredArgsConstructor
    class Primitive<P> implements PersistentDataType<P, P> {
        private final Class<P> primitiveType;

        @Override
        public Class<P> getPrimitiveType() {
            return primitiveType;
        }

        @Override
        public Class<P> getComplexType() {
            return primitiveType;
        }

        @Override
        public P toPrimitive(P complex, PersistentDataAdapterContext context) {
            return complex;
        }

        @Override
        public P fromPrimitive(P primitive, PersistentDataAdapterContext context) {
            return primitive;
        }
    }

    /**
     * A special implementation to convert between Byte and Boolean, as there is no native implementation for booleans.
     * Any byte value not equal to 0 is considered true.
     */
    class Boolean implements PersistentDataType<Byte, java.lang.Boolean> {
        @Override
        public Class<Byte> getPrimitiveType() {
            return Byte.class;
        }

        @Override
        public Class<java.lang.Boolean> getComplexType() {
            return java.lang.Boolean.class;
        }

        @Override
        public Byte toPrimitive(java.lang.Boolean complex, PersistentDataAdapterContext context) {
            return (byte) (complex ? 1 : 0);
        }

        @Override
        public java.lang.Boolean fromPrimitive(Byte primitive, PersistentDataAdapterContext context) {
            return primitive != 0;
        }
    }
}
