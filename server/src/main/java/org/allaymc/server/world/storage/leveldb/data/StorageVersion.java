package org.allaymc.server.world.storage.leveldb.data;

/**
 * {@code StorageVersion} names
 * world-storage format slots whose ordinals identify {@code level.dat} versions.
 * {@code WorldDataCodec} writes the final strict-size slot and rejects numerically
 * newer saved versions except its {@code Integer.MAX_VALUE} missing-value sentinel.
 * The preceding constants preserve older ordinal
 * identities rather than selecting separate decoder implementations here.
 *
 * @author daoge_cmd
 */
public enum StorageVersion {
    /**
     * Unspecified storage format slot.
     */
    UNKNOWN,
    /**
     * Legacy storage slot one.
     */
    OLD_V1,
    /**
     * Legacy storage slot two.
     */
    OLD_V2,
    /**
     * Legacy storage slot three.
     */
    OLD_V3,
    /**
     * Initial LevelDB storage slot.
     */
    LEVELDB,
    /**
     * LevelDB subchunk storage slot.
     */
    LEVELDB_SUB_CHUNKS,
    /**
     * Raw-compressed subchunk storage slot.
     */
    LEVELDB_SUB_CHUNK_RAW_ZIP,
    /**
     * Paletted LevelDB storage slot.
     */
    LEVELDB_PALETTED,
    /**
     * Multiple-block-storage paletted format slot.
     */
    LEVELDB_PALETTED_MULTI_BLOCK_STORAGE,
    /**
     * Upgraded-seed metadata format slot.
     */
    LEVEL_DATA_UPGRADED_SEED,
    /**
     * Strict-size metadata format slot.
     */
    LEVEL_DATA_STRICT_SIZE
}
