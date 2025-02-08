package org.allaymc.api.pdc;

import lombok.RequiredArgsConstructor;

/**
 * This class represents an enum with a generic content type. It defines the
 * types a custom tag can have.
 * <p>
 * This interface can be used to create your own custom
 * {@link PersistentDataType} with different complex types. This may be useful
 * for the likes of a UUIDTagType:
 * <pre>
 * {@code
 * public class UUIDTagType implements PersistentDataType<byte[], UUID> {
 *         @Override
 *         public Class<byte[]> getPrimitiveType() {
 *             return byte[].class;
 *         }
 *
 *         @Override
 *         public Class<UUID> getComplexType() {
 *             return UUID.class;
 *         }
 *
 *         @Override
 *         public byte[] toPrimitive(UUID complex, PersistentDataAdapterContext context) {
 *             var buffer = ByteBuffer.wrap(new byte[16]);
 *             buffer.putLong(complex.getMostSignificantBits());
 *             buffer.putLong(complex.getLeastSignificantBits());
 *             return buffer.array();
 *         }
 *
 *         @Override
 *         public UUID fromPrimitive(byte[] primitive, PersistentDataAdapterContext context) {
 *             var buffer = ByteBuffer.wrap(primitive);
 *             var firstLong = buffer.getLong();
 *             var secondLong = buffer.getLong();
 *             return new UUID(firstLong, secondLong);
 *         }
 *     }
 * }</pre>
 * <p>
 * Any plugin owned implementation of this interface is required to define one
 * of the existing primitive types found in this interface. Notably
 * {@link #BOOLEAN} is not a primitive type but a convenience type.
 *
 * @param <P> the primary object type that is stored in the given tag
 * @param <C> the retrieved object type when applying this tag type
 */
public interface PersistentDataType<P, C> {

    /*The primitive one value types.*/
    PersistentDataType<Byte, Byte> BYTE = new Primitive<>(Byte.class);
    PersistentDataType<Short, Short> SHORT = new Primitive<>(Short.class);
    PersistentDataType<Integer, Integer> INTEGER = new Primitive<>(Integer.class);
    PersistentDataType<Long, Long> LONG = new Primitive<>(Long.class);
    PersistentDataType<Float, Float> FLOAT = new Primitive<>(Float.class);
    PersistentDataType<Double, Double> DOUBLE = new Primitive<>(Double.class);

    /**
     * A convenience implementation to convert between Byte and Boolean as there is
     * no native implementation for booleans. <br>
     * Any byte value not equal to 0 is considered to be true.
     */
    PersistentDataType<Byte, java.lang.Boolean> BOOLEAN = new Boolean();

    PersistentDataType<String, String> STRING = new Primitive<>(String.class);

    /*Primitive Arrays*/
    PersistentDataType<byte[], byte[]> BYTE_ARRAY = new Primitive<>(byte[].class);
    PersistentDataType<int[], int[]> INTEGER_ARRAY = new Primitive<>(int[].class);
    PersistentDataType<long[], long[]> LONG_ARRAY = new Primitive<>(long[].class);

    PersistentDataType<PersistentDataContainer, PersistentDataContainer> TAG_CONTAINER = new Primitive<>(PersistentDataContainer.class);

    /**
     * A data type provider type that itself cannot be used as a
     * {@link PersistentDataType}.
     * <p>
     * {@link ListPersistentDataTypeProvider} exposes shared persistent data
     * types for storing lists of other data types, however.
     * <p>
     * Its existence in the {@link PersistentDataType} interface does not permit
     * {@link java.util.List} as a primitive type in combination with a plain
     * {@link PersistentDataType}. {@link java.util.List}s are only valid
     * primitive types when used via a {@link ListPersistentDataType}.
     *
     * @see ListPersistentDataTypeProvider
     */
    ListPersistentDataTypeProvider LIST = new ListPersistentDataTypeProvider();

    /**
     * Returns the primitive data type of this tag.
     *
     * @return the class
     */
    Class<P> getPrimitiveType();

    /**
     * Returns the complex object type the primitive value resembles.
     *
     * @return the class type
     */
    Class<C> getComplexType();

    /**
     * Returns the primitive data that resembles the complex object passed to
     * this method.
     *
     * @param complex the complex object instance
     * @param context the context this operation is running in
     *
     * @return the primitive value
     */
    P toPrimitive(C complex, PersistentDataAdapterContext context);

    /**
     * Creates a complex object based of the passed primitive value
     *
     * @param primitive the primitive value
     * @param context   the context this operation is running in
     *
     * @return the complex object instance
     */

    C fromPrimitive(P primitive, PersistentDataAdapterContext context);

    /**
     * A default implementation that simply exists to pass on the retrieved or
     * inserted value to the next layer.
     * <p>
     * This implementation does not add any kind of logic, but is used to
     * provide default implementations for the primitive types.
     *
     * @param <P> the generic type of the primitive objects
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
     * A convenience implementation to convert between Byte and Boolean as there is
     * no native implementation for booleans. <br>
     * Any byte value not equal to 0 is considered to be true.
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
