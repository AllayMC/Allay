package org.allaymc.server.pdc;

import org.allaymc.api.pdc.PersistentDataAdapterContext;
import org.allaymc.api.pdc.PersistentDataType;

import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * {@code UUIDDataType} supplies the test converter between UUID values and two big-endian
 * longs stored in a byte array.
 *
 * @author IWareQ
 */
public class UUIDDataType implements PersistentDataType<byte[], UUID> {
    /**
     * Returns the byte-array primitive class used to persist test UUIDs.
     */
    @Override
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    /**
     * Returns the {@code UUID} class exposed by this test converter.
     */
    @Override
    public Class<UUID> getComplexType() {
        return UUID.class;
    }

    /**
     * Encodes the UUID's most significant long before its least significant long. The result is
     * a new sixteen-byte array in {@code ByteBuffer}'s default big-endian order, and {@code null}
     * UUIDs cannot be encoded.
     */
    @Override
    public byte[] toPrimitive(UUID complex, PersistentDataAdapterContext context) {
        // Each UUID contains two eight-byte longs; the fixed buffer preserves both halves in
        // the same order consumed by fromPrimitive during the test round trip.
        var buffer = ByteBuffer.wrap(new byte[16]);
        buffer.putLong(complex.getMostSignificantBits());
        buffer.putLong(complex.getLeastSignificantBits());
        return buffer.array();
    }

    /**
     * Decodes the first sixteen bytes as the UUID's two big-endian longs. Short arrays fail with
     * buffer underflow, while extra trailing bytes are ignored; the input array is read without
     * modification.
     */
    @Override
    public UUID fromPrimitive(byte[] primitive, PersistentDataAdapterContext context) {
        var buffer = ByteBuffer.wrap(primitive);
        var firstLong = buffer.getLong();
        var secondLong = buffer.getLong();
        return new UUID(firstLong, secondLong);
    }
}
