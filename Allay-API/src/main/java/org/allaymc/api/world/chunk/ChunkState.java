package org.allaymc.api.world.chunk;

/**
 * Allay Project 2023/9/10
 *
 * @author daoge_cmd
 */
public enum ChunkState {
    EMPTY,
    STRUCTURE_STARTS,
    STRUCTURE_REFERENCES,
    BIOMES,
    NOISE,
    SURFACE,
    CARVERS,
    FEATURES,
    INITIALIZE_LIGHT,
    LIGHT,
    SPAWN,
    FINISHED
}
