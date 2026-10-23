package org.allaymc.api.pdc;

import lombok.RequiredArgsConstructor;

/**
 * {@code PersistentDataType} converts custom metadata between an NBT-supported primitive
 * representation and a complex application value. The primitive class selects a registry
 * adapter, while the complex class describes the value returned to callers; custom converters
 * are responsible for validating their own representation.
 *
 * <p>
 * {@code BYTE}, {@code SHORT}, {@code INTEGER}, {@code LONG}, {@code FLOAT}, and
 * {@code DOUBLE} use boxed numeric classes without changing numeric widths. {@code STRING}
 * also passes its value through unchanged. {@code BYTE_ARRAY}, {@code INTEGER_ARRAY}, and
 * {@code LONG_ARRAY} retain array references during conversion, so callers manage mutable
 * storage sharing. {@code TAG_CONTAINER} supplies nested compound values whose registry
 * adapter controls structural conversion and sharing.
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

    /**
     * Boxed byte value adapter.
     */
    PersistentDataType<Byte, Byte> BYTE = new Primitive<>(Byte.class);
    /**
     * Boxed short value adapter.
     */
    PersistentDataType<Short, Short> SHORT = new Primitive<>(Short.class);
    /**
     * Boxed integer value adapter.
     */
    PersistentDataType<Integer, Integer> INTEGER = new Primitive<>(Integer.class);
    /**
     * Boxed long value adapter.
     */
    PersistentDataType<Long, Long> LONG = new Primitive<>(Long.class);
    /**
     * Boxed float value adapter.
     */
    PersistentDataType<Float, Float> FLOAT = new Primitive<>(Float.class);
    /**
     * Boxed double value adapter.
     */
    PersistentDataType<Double, Double> DOUBLE = new Primitive<>(Double.class);

    /**
     * {@code BOOLEAN} represents booleans through the byte adapter. Encoding uses {@code 1} and
     * {@code 0}, while decoding treats every nonzero byte as {@code true}.
     */
    PersistentDataType<Byte, java.lang.Boolean> BOOLEAN = new Boolean();

    /**
     * Unmodified string value adapter.
     */
    PersistentDataType<String, String> STRING = new Primitive<>(String.class);

    /**
     * Mutable byte-array value adapter.
     */
    PersistentDataType<byte[], byte[]> BYTE_ARRAY = new Primitive<>(byte[].class);
    /**
     * Mutable integer-array value adapter.
     */
    PersistentDataType<int[], int[]> INTEGER_ARRAY = new Primitive<>(int[].class);
    /**
     * Mutable long-array value adapter.
     */
    PersistentDataType<long[], long[]> LONG_ARRAY = new Primitive<>(long[].class);

    /**
     * Nested persistent container adapter.
     */
    PersistentDataType<PersistentDataContainer, PersistentDataContainer> TAG_CONTAINER = new Primitive<>(PersistentDataContainer.class);

    /**
     * {@code LIST} supplies built-in list adapters and creates custom element adapters. The
     * registry
     * uses each adapter's element type to choose the NBT list representation.
     */
    ListPersistentDataTypeProvider LIST = new ListPersistentDataTypeProvider();

    /**
     * Returns the primitive class used to select the registry adapter.
     */
    Class<P> getPrimitiveType();

    /**
     * Returns the application class represented by decoded persistent data values.
     */
    Class<C> getComplexType();

    /**
     * Converts an application value to the primitive representation accepted by its registry
     * adapter. The converter controls validation and copying, and it may use {@code context} to
     * create nested containers when a compound representation is needed.
     */
    P toPrimitive(C complex, PersistentDataAdapterContext context);

    /**
     * Converts a stored primitive representation to an application value. Implementations must
     * validate any representation requirements beyond the primitive class, such as byte-array
     * length, because a primitive-type presence check does not establish those requirements.
     */
    C fromPrimitive(P primitive, PersistentDataAdapterContext context);

    /**
     * {@code Primitive} represents values whose primitive and complex classes are identical.
     * Both conversions retain the supplied reference, including {@code null} and mutable arrays;
     * the container's registry can impose additional storage validation.
     *
     * @param <P> unchanged value representation
     */
    @RequiredArgsConstructor
    class Primitive<P> implements PersistentDataType<P, P> {
        /**
         * Shared primitive and complex class.
         */
        private final Class<P> primitiveType;

        /**
         * Returns the retained primitive class used for adapter selection.
         */
        @Override
        public Class<P> getPrimitiveType() {
            return primitiveType;
        }

        /**
         * Returns the same retained class used for primitive values.
         */
        @Override
        public Class<P> getComplexType() {
            return primitiveType;
        }

        /**
         * Returns {@code complex} unchanged, without using {@code context}. The returned reference
         * can
         * be {@code null} or mutable, so conversion alone provides no validation or independent
         * copy
         * before registry storage.
         */
        @Override
        public P toPrimitive(P complex, PersistentDataAdapterContext context) {
            return complex;
        }

        /**
         * Returns {@code primitive} unchanged, without using {@code context}. Mutable values
         * remain
         * shared with their source, so callers must not treat this conversion as a defensive copy
         * of
         * stored arrays or other objects.
         */
        @Override
        public P fromPrimitive(P primitive, PersistentDataAdapterContext context) {
            return primitive;
        }
    }

    /**
     * {@code Boolean} represents boolean metadata through a byte-compatible primitive adapter.
     * Encoding uses {@code 1} for {@code true} and {@code 0} for {@code false}; decoding accepts
     * every nonzero byte as {@code true}.
     */
    class Boolean implements PersistentDataType<Byte, java.lang.Boolean> {
        /**
         * Returns the boxed byte class used to persist boolean values.
         */
        @Override
        public Class<Byte> getPrimitiveType() {
            return Byte.class;
        }

        /**
         * Returns the boxed boolean class exposed to application callers.
         */
        @Override
        public Class<java.lang.Boolean> getComplexType() {
            return java.lang.Boolean.class;
        }

        /**
         * Encodes a boolean as {@code 1} or {@code 0}. The conversion ignores {@code context} and
         * unboxes {@code complex}, so a {@code null} value fails before any byte representation
         * can
         * be stored.
         */
        @Override
        public Byte toPrimitive(java.lang.Boolean complex, PersistentDataAdapterContext context) {
            return (byte) (complex ? 1 : 0);
        }

        /**
         * Decodes zero as {@code false} and every other byte as {@code true}. The conversion
         * ignores
         * {@code context} and unboxes {@code primitive}, so {@code null} is not a valid encoded
         * boolean value.
         */
        @Override
        public java.lang.Boolean fromPrimitive(Byte primitive, PersistentDataAdapterContext context) {
            return primitive != 0;
        }
    }
}
