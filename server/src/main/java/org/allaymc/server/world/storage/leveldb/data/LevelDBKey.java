package org.allaymc.server.world.storage.leveldb.data;

import org.allaymc.api.world.chunk.ChunkState;
import org.allaymc.api.world.dimension.DimensionType;
import org.allaymc.server.utils.Utils;
import org.allaymc.server.world.dimension.DimensionId;
import org.jetbrains.annotations.Range;

/**
 * {@code LevelDBKey} identifies chunk-record discriminators and builds keys
 * for chunk records and independently indexed entities.
 * Chunk keys contain little-endian horizontal coordinates, an optional
 * dimension identifier, and a record discriminator. Section keys append a
 * signed section-coordinate byte, while actor keys use a persistent entity
 * identifier rather than a chunk position.
 *
 * <p>
 * The overworld omits the dimension field, so its key layout differs from every
 * other dimension. Actor digest keys reuse exactly that chunk prefix to
 * associate entity identifiers with the correct chunk and dimension. The
 * negative discriminators name Allay-specific records. Stored discriminator
 * values and byte ordering are compatibility identities: changing them would
 * select different database records rather than merely changing an internal
 * label.
 *
 * @author Cool_Loong | Cloudburst Server
 */
public enum LevelDBKey {
    /**
     * Three-dimensional height and biome record.
     */
    DATA_3D(43),
    /**
     * Current chunk-format version byte.
     */
    VERSION(44),
    /**
     * Legacy two-dimensional height and biome record.
     */
    DATA_2D(45),
    /**
     * Earlier height and biome record discriminator.
     */
    DATA_2D_LEGACY(46),
    /**
     * Individual chunk-section block palette record.
     */
    CHUNK_SECTION_PREFIX(47),
    /**
     * Legacy terrain record discriminator.
     */
    LEGACY_TERRAIN(48),
    /**
     * Consecutive block-entity NBT compounds.
     */
    BLOCK_ENTITIES(49),
    /**
     * {@code ENTITIES} identifies legacy consecutive entity NBT compounds. The
     * storage loader reads this record only when the actor digest is absent.
     */
    ENTITIES(50),
    /**
     * Pending block-update NBT record.
     */
    PENDING_TICKS(51),
    /**
     * Legacy extra-block record discriminator.
     */
    BLOCK_EXTRA_DATA(52),
    /**
     * Biome-state record discriminator.
     */
    BIOME_STATE(53),
    /**
     * {@code CHUNK_FINALIZED_STATE} identifies vanilla generation-state data.
     * This storage reads and writes its state as a single ordinal byte.
     */
    CHUNK_FINALIZED_STATE(54),
    /**
     * Conversion metadata record discriminator.
     */
    CONVERSION_DATA(55),
    /**
     * Border-block record discriminator.
     */
    BORDER_BLOCKS(56),
    /**
     * Structure-spawn record discriminator.
     */
    HARDCODED_SPAWNERS(57),
    /**
     * Random-tick record discriminator.
     */
    RANDOM_TICKS(58),
    /**
     * Checksum record discriminator.
     */
    CHECK_SUMS(59),
    /**
     * Generation-seed record discriminator.
     */
    GENERATION_SEED(60),
    /**
     * Pre-expansion blending record discriminator.
     */
    GENERATED_PRE_CAVES_AND_CLIFFS_BLENDING(61),
    /**
     * Blending biome-height record discriminator.
     */
    BLENDING_BIOME_HEIGHT(62),
    /**
     * Metadata-hash record discriminator.
     */
    META_DATA_HASH(63),
    /**
     * Terrain-blending record discriminator.
     */
    BLENDING_DATA(64),
    /**
     * Actor-digest version record discriminator.
     */
    ACTOR_DIGEST_VERSION(65),
    /**
     * {@code LEGACY_VERSION} identifies the legacy chunk-version byte. The
     * storage loader checks this key when the current version key is absent.
     */
    LEGACY_VERSION(118),
    /**
     * Bounding-volume record discriminator.
     */
    AABB_VOLUMES(119),


    /**
     * {@code ALLAY_CHUNK_STATE} stores the Allay generation-state name as text.
     * The storage loader reconstructs the corresponding {@link ChunkState}
     * constant.
     */
    ALLAY_CHUNK_STATE(-1),
    /**
     * Allay point-of-interest NBT record.
     */
    ALLAY_POI_DATA(-2);

    /**
     * {@code ENTITY_DIGEST_PREFIX} prefixes
     * the chunk-to-actor digest keys whose values list persistent
     * entity identifiers in little-endian order.
     */
    private static final byte[] ENTITY_DIGEST_PREFIX = "digp".getBytes();
    /**
     * {@code ENTITY_PREFIX} prefixes
     * independent actor-record keys whose suffix is the persistent
     * entity identifier encoded in little-endian order.
     */
    private static final byte[] ENTITY_PREFIX = "actorprefix".getBytes();

    /**
     * Stored record discriminator.
     */
    private final byte encoded;

    /**
     * Stores the discriminator as its low eight bits in a byte. Enum
     * declarations supply signed-byte values; the range annotation documents
     * that intent but the constructor performs no runtime validation.
     */
    LevelDBKey(@Range(from = -128, to = 127) int encoded) {
        this.encoded = (byte) encoded;
    }

    /**
     * Returns the coordinate prefix shared by chunk records and actor digests.
     * X and Z are signed chunk coordinates represented by four little-endian
     * bytes each.
     *
     * <p>
     * The overworld prefix ends after Z, while other dimensions append their
     * four-byte identifier. This asymmetry is part of the persisted key layout:
     * always including the overworld identifier would address different
     * records. The returned array is newly assembled, and callers append their
     * record-specific discriminator or use it behind an actor-digest prefix.
     */
    public static byte[] indexChunk(int chunkX, int chunkZ, DimensionType dimensionType) {
        // The persisted overworld key omits a dimension field; append that
        // field only for other dimensions so existing chunk and digest keys
        // remain addressable.
        if (DimensionId.OVERWORLD.matches(dimensionType)) {
            return org.allaymc.server.utils.Utils.appendBytes(intToBytes(chunkX), intToBytes(chunkZ));
        }

        return org.allaymc.server.utils.Utils.appendBytes(intToBytes(chunkX), intToBytes(chunkZ), intToBytes(dimensionType.getId()));
    }

    /**
     * Returns a newly assembled actor-record key for the supplied persistent
     * entity identifier. The identifier is encoded in eight little-endian bytes
     * after the actor prefix and must not be a network runtime identifier.
     */
    public static byte[] indexEntity(long entityUniqueId) {
        return org.allaymc.server.utils.Utils.appendBytes(ENTITY_PREFIX, longToBytes(entityUniqueId));
    }

    /**
     * Returns the digest key for the supplied chunk and dimension. Its
     * coordinate suffix uses the same overworld dimension omission as ordinary
     * chunk keys, allowing the digest to locate that chunk's actor identifiers.
     */
    public static byte[] createEntityIdsKey(int chunkX, int chunkZ, DimensionType dimensionType) {
        return org.allaymc.server.utils.Utils.appendBytes(ENTITY_DIGEST_PREFIX, indexChunk(chunkX, chunkZ, dimensionType));
    }

    /**
     * Returns four little-endian bytes preserving the supplied integer bit
     * pattern.
     */
    private static byte[] intToBytes(int value) {
        return new byte[]{
                (byte) (value & 0xff),
                (byte) ((value >>> 8) & 0xff),
                (byte) ((value >>> 16) & 0xff),
                (byte) ((value >>> 24) & 0xff)
        };
    }

    /**
     * Returns eight little-endian bytes preserving the supplied {@code long} bit
     * pattern.
     */
    private static byte[] longToBytes(long value) {
        return new byte[]{
                (byte) (value & 0xff),
                (byte) ((value >>> 8) & 0xff),
                (byte) ((value >>> 16) & 0xff),
                (byte) ((value >>> 24) & 0xff),
                (byte) ((value >>> 32) & 0xff),
                (byte) ((value >>> 40) & 0xff),
                (byte) ((value >>> 48) & 0xff),
                (byte) ((value >>> 56) & 0xff)
        };
    }

    /**
     * Returns this record type's key for the requested chunk and dimension. The
     * returned array appends the stored discriminator to the coordinate prefix
     * without adding a section-coordinate suffix.
     */
    public byte[] createKey(int chunkX, int chunkZ, DimensionType dimensionType) {
        return org.allaymc.server.utils.Utils.appendBytes(indexChunk(chunkX, chunkZ, dimensionType), new byte[]{this.encoded});
    }

    /**
     * Returns a section key with the supplied section coordinate encoded in its
     * final byte. Only {@link #CHUNK_SECTION_PREFIX} supports this overload;
     * other constants throw an illegal argument exception, and the coordinate
     * is narrowed without validation.
     */
    public byte[] createKey(int chunkX, int chunkZ, int chunkSectionY, DimensionType dimensionType) {
        if (this != CHUNK_SECTION_PREFIX) {
            throw new IllegalArgumentException("The method must be used with CHUNK_SECTION_PREFIX!");
        }

        // The section-coordinate suffix preserves only the low byte, so callers
        // must supply section coordinates representable by the stored
        // signed-byte layout.
        return Utils.appendBytes(indexChunk(chunkX, chunkZ, dimensionType), new byte[]{this.encoded, (byte) chunkSectionY});
    }
}
