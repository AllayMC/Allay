package org.allaymc.server.world.storage.leveldb.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import lombok.experimental.UtilityClass;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.function.Consumer;

/**
 * Shared utility methods for LevelDB world storage codecs.
 */
@UtilityClass
public final class LevelDBUtils {

    public static byte[] int2ByteArrayLE(int value) {
        return ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(value).array();
    }

    public static byte[] withByteBufToArray(Consumer<ByteBuf> writer) {
        var buf = ByteBufAllocator.DEFAULT.buffer();
        try {
            writer.accept(buf);
            return ByteBufUtil.getBytes(buf);
        } finally {
            buf.release();
        }
    }
}
