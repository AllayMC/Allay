package org.allaymc.server.world.storage.leveldb.data;

/**
 * @author daoge_cmd
 */
public enum StorageVersion {
    UNKNOWN,
    OLD_V1,
    OLD_V2,
    OLD_V3,
    LEVELDB,
    LEVELDB_SUB_CHUNKS,
    LEVELDB_SUB_CHUNK_RAW_ZIP,
    LEVELDB_PALETTED,
    LEVELDB_PALETTED_MULTI_BLOCK_STORAGE,
    LEVEL_DATA_UPGRADED_SEED,
    LEVEL_DATA_STRICT_SIZE
}
