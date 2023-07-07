package cn.allay.api.world.chunk;

import org.jetbrains.annotations.Nullable;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
public interface ChunkAccessible {
    @Nullable
    Chunk getChunk(int x, int z);

    void setChunk(int x, int z, Chunk chunk);

    int maxX();

    int maxZ();

    int minX();

    int minZ();
}
