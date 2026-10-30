package org.allaymc.server.world.storage.leveldb.data;

/**
 * {@code VanillaChunkState} names
 * vanilla chunk-generation states using their persisted ordinal values.
 * The LevelDB loader accepts the done state as completed terrain and treats
 * other stored states as a new chunk; an absent state record follows the
 * separate legacy-loading path.
 *
 * @author daoge_cmd
 */
public enum VanillaChunkState {
    /**
     * Chunk needs to be ticked.
     */
    NEEDS_INSTANT_TICK,
    /**
     * Chunk needs to be populated.
     */
    NEEDS_POPULATION,
    /**
     * Completed vanilla terrain state accepted by the LevelDB chunk loader.
     */
    DONE
}
