package cn.allay.server.world.chunk;

import cn.allay.api.world.chunk.Chunk;
import cn.allay.api.world.generator.LimitedWorldRegion;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
public class SingleChunkLimitedWorldRegion implements LimitedWorldRegion {

    private Chunk chunk;

    public SingleChunkLimitedWorldRegion(Chunk chunk) {
        this.chunk = chunk;
    }

    @Override
    public @Nullable Chunk getChunk(int x, int z) {
        if (invalidXZ(x, z))
            return null;
        return chunk;
    }

    @Override
    public void setChunk(int x, int z, Chunk chunk) {
        if (invalidXZ(x, z))
            return;
        this.chunk = chunk;
    }

    @Override
    public void setChunk(int x, int z, Chunk chunk, Consumer<Chunk> chunkAddingCallback) {
        //todo SingleChunkLimitedWorldRegion#setChunk
    }

    private boolean invalidXZ(int x, int z) {
        return x != 0 || z != 0;
    }

    @Override
    public int maxX() {
        return 0;
    }

    @Override
    public int maxZ() {
        return 0;
    }

    @Override
    public int minX() {
        return 0;
    }

    @Override
    public int minZ() {
        return 0;
    }
}
