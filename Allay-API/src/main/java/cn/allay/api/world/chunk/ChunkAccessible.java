package cn.allay.api.world.chunk;

import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
public interface ChunkAccessible {
    @Nullable
    Chunk getChunk(int x, int z);

    void setChunk(int x, int z, Chunk chunk);

    void setChunk(int x, int z, Chunk chunk, Consumer<Chunk> chunkAddingCallback);

    int maxX();

    int maxZ();

    int minX();

    int minZ();
}
