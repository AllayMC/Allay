package org.allaymc.server.world.chunk;

import java.util.Arrays;

/**
 * ChunkBitMap stores flags for each (x, z) pos in a chunk.
 *
 * @author daoge_cmd
 */
public final class ChunkBitMap {

    // Use a byte array to store flags for each position in a 16x16 chunk.
    // Each byte stores flags for 8 positions, so we need 32 bytes for 16x16 positions.
    private final byte[] bitMap = new byte[32];

    private static void checkXZ(int x, int z) {
        // Ensure (x, z) coordinates are within the valid range
        if (x < 0 || x >= 16 || z < 0 || z >= 16) {
            throw new IllegalArgumentException("Coordinates (x, z) must be between 0 and 15.");
        }
    }

    // Set the flag for position (x, z) in the chunk
    public void set(int x, int z, boolean value) {
        checkXZ(x, z);

        // Calculate the index in the byte array and the bit shift within the byte
        int index = (x * 16 + z) / 8;  // Determine which byte to modify
        int shift = (x * 16 + z) % 8;  // Determine the bit position within the byte

        if (value) {
            bitMap[index] |= (byte) (1 << (7 - shift)); // Set the bit at the specified position to 1
        } else {
            bitMap[index] &= (byte) ~(1 << (7 - shift)); // Set the bit at the specified position to 0
        }
    }

    // Get the flag value for position (x, z) in the chunk
    public boolean get(int x, int z) {
        checkXZ(x, z);

        // Calculate the index in the byte array and the bit shift within the byte
        int index = (x * 16 + z) / 8;
        int shift = (x * 16 + z) % 8;

        return (bitMap[index] & (1 << (7 - shift))) != 0; // Check if the bit at the specified position is 1
    }

    // Clear all flags (set all bits to 0)
    public void clear() {
        Arrays.fill(bitMap, (byte) 0);
    }
}
