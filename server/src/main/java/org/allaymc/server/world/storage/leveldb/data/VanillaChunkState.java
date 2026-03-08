package org.allaymc.server.world.storage.leveldb.data;

/**
 * Indicates the state of generation of the chunk in vanilla.
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
     * Chunk is fully finished (which is the state generally found in world saves from vanilla).
     */
    DONE
}
