package org.allaymc.server.world.chunk;

import java.util.Arrays;

/**
 * {@code ChunkBitMap} stores
 * one {@code boolean} flag for every horizontal column in a chunk. Columns use
 * X-major order, with each group of eight occupying a byte and earlier columns
 * assigned the more significant bits.
 *
 * <p>
 * Updates select one bit and preserve the remaining columns in that byte. Both
 * coordinate axes are checked before indexing, so invalid positions fail rather
 * than aliasing another flag. Instances begin with all flags clear and can be
 * cleared together. The byte array is private, but read-modify-write updates
 * are unsynchronized; a caller sharing this map must coordinate access,
 * including changes to different columns occupying the same byte.
 *
 * @author daoge_cmd
 */
public final class ChunkBitMap {
    /**
     * {@code bitMap} holds packed column flags, initially clear. Callers
     * serialize shared access because bit updates have no internal locking.
     */
    private final byte[] bitMap = new byte[32];

    /**
     * Rejects horizontal coordinates outside the local zero-through-fifteen
     * range with {@link IllegalArgumentException}. Validation prevents invalid
     * X or Z values from selecting another column through the packed index
     * calculation.
     */
    private static void checkXZ(int x, int z) {
        if (x < 0 || x >= 16 || z < 0 || z >= 16) {
            throw new IllegalArgumentException("Coordinates (x, z) must be between 0 and 15.");
        }
    }

    /**
     * Replaces one column flag while preserving the other seven flags sharing
     * its byte. The X-major column index selects a byte, and its remainder
     * selects a bit from most to least significant. Invalid horizontal chunk-local coordinates
     * throw {@link IllegalArgumentException} before any backing storage
     * changes. Updates are read-modify-write operations, so callers must
     * synchronize writers even when they target different columns in the same
     * byte.
     */
    public void set(int x, int z, boolean value) {
        checkXZ(x, z);
        // A mask changes only the selected column, preserving neighboring dirty
        // flags that share the same byte in the chunk height cache.
        int index = (x * 16 + z) / 8;
        int shift = (x * 16 + z) % 8;

        if (value) {
            bitMap[index] |= (byte) (1 << (7 - shift));
        } else {
            bitMap[index] &= (byte) ~(1 << (7 - shift));
        }
    }

    /**
     * Returns the flag for local horizontal coordinates from zero through
     * fifteen. Invalid coordinates throw {@link IllegalArgumentException};
     * callers sharing the map must coordinate reads with unsynchronized flag
     * updates.
     */
    public boolean get(int x, int z) {
        checkXZ(x, z);
        // The most-significant-bit ordering matches set(), so reading one column
        // ignores other flags stored alongside it in the same byte.
        int index = (x * 16 + z) / 8;
        int shift = (x * 16 + z) % 8;

        return (bitMap[index] & (1 << (7 - shift))) != 0;
    }

    /**
     * Clears every column flag in this map's existing storage.
     */
    public void clear() {
        Arrays.fill(bitMap, (byte) 0);
    }
}
