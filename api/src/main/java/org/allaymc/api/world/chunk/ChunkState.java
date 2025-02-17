package org.allaymc.api.world.chunk;

/**
 * ChunkState represents the state of a chunk.
 *
 * @author daoge_cmd
 */
public enum ChunkState {
    NEW,
    NOISING,
    NOISED,
    POPULATING,
    POPULATED,
    POST_PROCESSING,
    POST_PROCESSED,
    FINISHED
}
