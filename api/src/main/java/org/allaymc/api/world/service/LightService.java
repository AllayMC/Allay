package org.allaymc.api.world.service;

import org.allaymc.api.world.chunk.Chunk;

/**
 * @author daoge_cmd
 */
public interface LightService {
    int getSkyLight(int x, int y, int z);

    int getInternalLight(int x, int y, int z);

    int getInternalSkyLight(int x, int y, int z);

    int getBlockLight(int x, int y, int z);

    void onBlockChange(int x, int y, int z, int lightEmission, int lightDampening);

    void onChunkLoad(Chunk chunk);

    void onChunkUnload(Chunk chunk);
}
