package org.allaymc.server.utils;

import org.allaymc.api.world.DimensionInfo;

/**
 * Allay Project 8/22/2023
 *
 * @author Cool_Loong | Cloudburst Server
 */
public enum LevelDBKeyUtils {
    /**
     * Biome IDs are written as 8-bit integers. No longer written since v1.18.0.
     */
    DATA_2D('-'),
    /**
     * Biomes are stored as palettes similar to blocks. Exactly 25 palettes are written. Biome IDs are written as integers.
     */
    DATA_3D('+'),
    /**
     * Version levelDB key after v1.16.100
     */
    VERSION(','),
    /**
     * Each entry of the biome array contains a biome ID in the first byte, and the final 3 bytes are red/green/blue respectively. No longer written since v1.0.0.
     */
    DATA_2D_LEGACY('.'),
    /**
     * Block data for a 16×16×16 chunk section
     */
    CHUNK_SECTION_PREFIX('/'),
    /**
     * Data ordered in XZY order, unlike Java.
     * No longer written since v1.0.0.
     * <p>
     * Biomes are IDs plus RGB colours similar to Data2DLegacy.
     */
    LEGACY_TERRAIN('0'),
    /**
     * Block entity data (little-endian NBT)
     */
    BLOCK_ENTITIES('1'),
    /**
     * Entity data (little-endian NBT)
     */
    ENTITIES('2'),
    /**
     * Pending tick data (little-endian NBT)
     */
    PENDING_TICKS('3'),
    /**
     * Array of blocks that appear in the same place as other blocks. Used for grass appearing inside snow layers prior to v1.2.13. No longer written as of v1.2.13.
     */
    BLOCK_EXTRA_DATA('4'),
    BIOME_STATE('5'),
    CHUNK_FINALIZED_STATE('6'),
    /**
     * Education Edition Feature
     */
    BORDER_BLOCKS('8'),
    /**
     * Bounding boxes for structure spawns stored in binary format
     */
    HARDCODED_SPAWNERS('9'),
    LEGACY_VERSION('v');

    private final byte encoded;

    LevelDBKeyUtils(char encoded) {
        this.encoded = (byte) encoded;
    }

    public byte[] getKey(int chunkX, int chunkZ) {
        return new byte[]{
                (byte) (chunkX & 0xff),
                (byte) ((chunkX >>> 8) & 0xff),
                (byte) ((chunkX >>> 16) & 0xff),
                (byte) ((chunkX >>> 24) & 0xff),
                (byte) (chunkZ & 0xff),
                (byte) ((chunkZ >>> 8) & 0xff),
                (byte) ((chunkZ >>> 16) & 0xff),
                (byte) ((chunkZ >>> 24) & 0xff),
                this.encoded
        };
    }

    public byte[] getKey(int chunkX, int chunkZ, DimensionInfo dimension) {
        if (dimension.equals(DimensionInfo.OVERWORLD)) {
            return new byte[]{
                    (byte) (chunkX & 0xff),
                    (byte) ((chunkX >>> 8) & 0xff),
                    (byte) ((chunkX >>> 16) & 0xff),
                    (byte) ((chunkX >>> 24) & 0xff),
                    (byte) (chunkZ & 0xff),
                    (byte) ((chunkZ >>> 8) & 0xff),
                    (byte) ((chunkZ >>> 16) & 0xff),
                    (byte) ((chunkZ >>> 24) & 0xff),
                    this.encoded
            };
        } else {
            byte dimensionId = (byte) dimension.dimensionId();
            return new byte[]{
                    (byte) (chunkX & 0xff),
                    (byte) ((chunkX >>> 8) & 0xff),
                    (byte) ((chunkX >>> 16) & 0xff),
                    (byte) ((chunkX >>> 24) & 0xff),
                    (byte) (chunkZ & 0xff),
                    (byte) ((chunkZ >>> 8) & 0xff),
                    (byte) ((chunkZ >>> 16) & 0xff),
                    (byte) ((chunkZ >>> 24) & 0xff),
                    (byte) (dimensionId & 0xff),
                    (byte) ((dimensionId >>> 8) & 0xff),
                    (byte) ((dimensionId >>> 16) & 0xff),
                    (byte) ((dimensionId >>> 24) & 0xff),
                    this.encoded
            };
        }
    }

    public byte[] getKey(int chunkX, int chunkZ, int chunkSectionY) {
        if (this.encoded != CHUNK_SECTION_PREFIX.encoded)
            throw new IllegalArgumentException("The method must be used with CHUNK_SECTION_PREFIX!");
        return new byte[]{
                (byte) (chunkX & 0xff),
                (byte) ((chunkX >>> 8) & 0xff),
                (byte) ((chunkX >>> 16) & 0xff),
                (byte) ((chunkX >>> 24) & 0xff),
                (byte) (chunkZ & 0xff),
                (byte) ((chunkZ >>> 8) & 0xff),
                (byte) ((chunkZ >>> 16) & 0xff),
                (byte) ((chunkZ >>> 24) & 0xff),
                this.encoded,
                (byte) chunkSectionY
        };
    }

    public byte[] getKey(int chunkX, int chunkZ, int chunkSectionY, DimensionInfo dimension) {
        if (this.encoded != CHUNK_SECTION_PREFIX.encoded)
            throw new IllegalArgumentException("The method must be used with CHUNK_SECTION_PREFIX!");
        if (dimension.equals(DimensionInfo.OVERWORLD)) {
            return new byte[]{
                    (byte) (chunkX & 0xff),
                    (byte) ((chunkX >>> 8) & 0xff),
                    (byte) ((chunkX >>> 16) & 0xff),
                    (byte) ((chunkX >>> 24) & 0xff),
                    (byte) (chunkZ & 0xff),
                    (byte) ((chunkZ >>> 8) & 0xff),
                    (byte) ((chunkZ >>> 16) & 0xff),
                    (byte) ((chunkZ >>> 24) & 0xff),
                    this.encoded,
                    (byte) chunkSectionY
            };
        } else {
            byte dimensionId = (byte) dimension.dimensionId();
            return new byte[]{
                    (byte) (chunkX & 0xff),
                    (byte) ((chunkX >>> 8) & 0xff),
                    (byte) ((chunkX >>> 16) & 0xff),
                    (byte) ((chunkX >>> 24) & 0xff),
                    (byte) (chunkZ & 0xff),
                    (byte) ((chunkZ >>> 8) & 0xff),
                    (byte) ((chunkZ >>> 16) & 0xff),
                    (byte) ((chunkZ >>> 24) & 0xff),
                    (byte) (dimensionId & 0xff),
                    (byte) ((dimensionId >>> 8) & 0xff),
                    (byte) ((dimensionId >>> 16) & 0xff),
                    (byte) ((dimensionId >>> 24) & 0xff),
                    this.encoded,
                    (byte) chunkSectionY
            };
        }
    }
}
