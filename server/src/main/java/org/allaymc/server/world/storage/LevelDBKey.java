package org.allaymc.server.world.storage;

import org.allaymc.api.world.DimensionInfo;
import org.jetbrains.annotations.Range;

/**
 * @author Cool_Loong | Cloudburst Server
 */
public enum LevelDBKey {
    /**
     * Holds 3-dimensional biomes for the entire chunk.
     */
    DATA_3D(43),
    /**
     * Holds a single byte of data with the version of the chunk.
     */
    VERSION(44),
    /**
     * The old way to store height and biome data. No longer used since v1.18.0 in worlds with world height
     * change. It was replaced by {@link #DATA_3D} in newer worlds which has 3-dimensional biomes.
     */
    DATA_2D(45),
    /**
     * Each entry of the biome array contains a biome ID in the first byte, and the final 3 bytes are
     * red/green/blue respectively. No longer written since v1.0.0.
     */
    DATA_2D_LEGACY(46),
    /**
     * Block data for a 16×16×16 chunk section.
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
     * Holds n amount of NBT compound tags appended to each other (not a TAG_List, just appended). The
     * compound tags contain the position of the block entities.
     */
    BLOCK_ENTITIES(49),
    /**
     * Holds n amount of NBT compound tags appended to each other (not a TAG_List, just appended). The
     * compound tags contain the position of the entities. This tag is no longer used in newer version
     * and is replaced by <a href="https://learn.microsoft.com/en-us/minecraft/creator/documents/actorstorage?view=minecraft-bedrock-stable">New Actor Storage</a>.
     */
    ENTITIES(50),
    /**
     * Holds an NBT structure containing all scheduled ticks that were pending in the chunk.
     */
    PENDING_TICKS(51),
    /**
     * Array of blocks that appear in the same place as other blocks. Used for grass appearing inside
     * snow layers prior to v1.2.13. No longer written as of v1.2.13.
     */
    BLOCK_EXTRA_DATA(52),
    BIOME_STATE(53),
    /**
     * A single LE int32 that indicates the state of generation of the chunk.
     * See {@link VanillaChunkState} for details.
     */
    CHUNK_FINALIZED_STATE(54),
    /**
     * Data that the converter provides, that are used at runtime for things like blending.
     */
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
    /**
     * Holds a list of checksums of some sort. It's not clear of what data this checksum is composed or what
     * these checksums are used for.
     */
    CHECK_SUMS(59),
    GENERATION_SEED(60),
    GENERATED_PRE_CAVES_AND_CLIFFS_BLENDING(61),
    BLENDING_BIOME_HEIGHT(62),
    META_DATA_HASH(63),
    BLENDING_DATA(64),
    ACTOR_DIGEST_VERSION(65),
    /**
     * Replaced by {@link #VERSION}. It is still used by vanilla to check compatibility, but vanilla no longer writes this tag.
     */
    LEGACY_VERSION(118),
    AABB_VOLUMES(119);

    /**
     * Used in the <a href="https://learn.microsoft.com/en-us/minecraft/creator/documents/actorstorage?view=minecraft-bedrock-stable">New Actor Storage</a>.
     */
    public static final String ENTITY_DIGEST_PREFIX = "digp";
    /**
     * Used in the <a href="https://learn.microsoft.com/en-us/minecraft/creator/documents/actorstorage?view=minecraft-bedrock-stable">New Actor Storage</a>.
     */
    public static final String ENTITY_PREFIX = "actorprefix";

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
        }

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

    public byte[] getKey(int chunkX, int chunkZ, int chunkSectionY) {
        if (this != CHUNK_SECTION_PREFIX) {
            throw new IllegalArgumentException("The method must be used with CHUNK_SECTION_PREFIX!");
        }

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
        if (this != CHUNK_SECTION_PREFIX) {
            throw new IllegalArgumentException("The method must be used with CHUNK_SECTION_PREFIX!");
        }

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
        }

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
