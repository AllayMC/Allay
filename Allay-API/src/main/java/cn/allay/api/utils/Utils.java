package cn.allay.api.utils;

import io.netty.buffer.ByteBuf;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Range;

import java.time.Duration;
import java.util.function.Supplier;

/**
 * Allay Project 2023/6/23
 *
 * @author daoge_cmd
 */
@UtilityClass
public class Utils {
    public byte[] appendBytes(byte[] bytes1, byte[]... bytes2) {
        int length = bytes1.length;
        for (byte[] bytes : bytes2) {
            length += bytes.length;
        }

        byte[] appendedBytes = new byte[length];
        System.arraycopy(bytes1, 0, appendedBytes, 0, bytes1.length);
        int index = bytes1.length;

        for (byte[] b : bytes2) {
            System.arraycopy(b, 0, appendedBytes, index, b.length);
            index += b.length;
        }
        return appendedBytes;
    }

    /**
     * Calculates the number of bit that the specified value convert to binary
     *
     * @param value the value
     * @return the bits
     */
    public byte computeRequiredBits(@Range(from = 0, to = Integer.MAX_VALUE) int value) {
        if (value <= 1) return 1;
        byte bits = 1;
        while (value >= (1 << bits)) {
            bits++;
        }
        return bits;
    }

    public byte[] convertByteBuf2Array(ByteBuf buf) {
        byte[] payload = new byte[buf.readableBytes()];
        buf.readBytes(payload);
        return payload;
    }

    /**
     * Spin until end = true
     *
     * @param end      the end
     * @param interval the interval
     */
    public void spinUntil(Supplier<Boolean> end, Duration interval) {
        while (end.get()) {
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
