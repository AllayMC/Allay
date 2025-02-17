package org.allaymc.server.world.storage;

import org.allaymc.api.world.DimensionInfo;
import org.jetbrains.annotations.Range;

/**
 * @author Cool_Loong | Cloudburst Server
 */
public enum LevelDBKey {
    /**
     * Biomes are stored as palettes similar to blocks. Exactly 25 palettes are written. Biome IDs are written as integers.
     */
    DATA_3D(43),
    /**
     * Version levelDB key after v1.16.100
     */
    VERSION(44),
    /**
     * Biome IDs are written as 8-bit integers. No longer written since v1.18.0.
     */
    DATA_2D(45),
    /**
     * Each entry of the biome array contains a biome ID in the first byte, and the final 3 bytes are red/green/blue respectively. No longer written since v1.0.0.
     */
    DATA_2D_LEGACY(46),
    /**
     * Block data for a 16×16×16 chunk section
     */
    CHUNK_SECTION_PREFIX(47),
    /**
     * Data ordered in XZY order, unlike Java.
     * No longer written since v1.0.0.
     * <p>
     * Biomes are IDs plus RGB colours similar to Data2DLegacy.
     */
    LEGACY_TERRAIN(48),
    /**
     * Block entity data (little-endian NBT)
     */
    BLOCK_ENTITIES(49),
    /**
     * Entity data (little-endian NBT)
     */
    ENTITIES(50),
    /**
     * Pending tick data (little-endian NBT)
     */
    PENDING_TICKS(51),
    /**
     * Array of blocks that appear in the same place as other blocks. Used for grass appearing inside snow layers prior to v1.2.13. No longer written as of v1.2.13.
     */
    BLOCK_EXTRA_DATA(52),
    BIOME_STATE(53),
    /**
     * The state of a chunk (byte)
     */
    CHUNK_FINALIZED_STATE(54),
    CONVERSION_DATA(55),
    /**
     * Education Edition Feature
     */
    BORDER_BLOCKS(56),
    /**
     * Bounding boxes for structure spawns stored in binary format
     */
    HARDCODED_SPAWNERS(57),
    RANDOM_TICKS(58),
    CHECK_SUMS(59),
    GENERATION_SEED(60),
    GENERATED_PRE_CAVES_AND_CLIFFS_BLENDING(61),
    BLENDING_BIOME_HEIGHT(62),
    META_DATA_HASH(63),
    BLENDING_DATA(64),
    ACTOR_DIGEST_VERSION(65),
    LEGACY_VERSION(118),
    AABB_VOLUMES(119);

    /*
     * NOTICE: The following keys are only used in allay,
     * which are not part of the vanilla levelDB format.
     *
     * The encoded values are incremented from -128 to
     * avoid conflicts with original keys.
     */

    // Noop

    private final byte encoded;

    // Just a convenient constructor to avoid explicit casting
    LevelDBKey(@Range(from = -128, to = 127) int encoded) {
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
        if (dimension == DimensionInfo.OVERWORLD) {
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
        if (this != CHUNK_SECTION_PREFIX)
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
        if (this != CHUNK_SECTION_PREFIX)
            throw new IllegalArgumentException("The method must be used with CHUNK_SECTION_PREFIX!");
        if (dimension == DimensionInfo.OVERWORLD) {
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
