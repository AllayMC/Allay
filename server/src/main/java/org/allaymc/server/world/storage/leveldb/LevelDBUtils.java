package org.allaymc.server.world.storage.leveldb;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import lombok.experimental.UtilityClass;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.function.Consumer;

/**
 * {@code LevelDBUtils} encodes
 * little-endian integers and collects bytes written by LevelDB codecs.
 * Buffer callbacks borrow temporary Netty storage only for the duration of the
 * call, and the returned byte array remains usable after that temporary buffer
 * is released.
 */
@UtilityClass
public final class LevelDBUtils {

    /**
     * Returns a new four-byte array containing the integer in little-endian
     * order.
     */
    public static byte[] int2ByteArrayLE(int value) {
        return ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(value).array();
    }

    /**
     * Returns the readable bytes produced by a callback using a temporary
     * buffer. The callback must neither release nor retain the borrowed buffer;
     * this method releases it even when writing fails.
     */
    public static byte[] withByteBufToArray(Consumer<ByteBuf> writer) {
        var buf = ByteBufAllocator.DEFAULT.buffer();
        try {
            writer.accept(buf);
            return ByteBufUtil.getBytes(buf);
        } finally {
            // The callback has finished or failed, so release its temporary
            // buffer after copying any successful output into the returned
            // array.
            buf.release();
        }
    }
}
