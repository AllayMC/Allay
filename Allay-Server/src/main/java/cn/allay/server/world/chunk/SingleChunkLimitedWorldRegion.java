package cn.allay.server.world.chunk;

import cn.allay.api.world.World;
import cn.allay.api.world.chunk.Chunk;
import cn.allay.api.world.generator.LimitedWorldRegion;
import org.jetbrains.annotations.Nullable;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
public class SingleChunkLimitedWorldRegion implements LimitedWorldRegion {

    private final World world;
    private Chunk chunk;

    public SingleChunkLimitedWorldRegion(World world, Chunk chunk) {
        this.world = world;
        this.chunk = chunk;
    }

    @Override
    public @Nullable Chunk getChunk(int x, int z) {
        if (invalidXZ(x, z))
            return null;
        return chunk;
    }

    @Override
    public @Nullable Chunk getChunk(long chunkHash) {
        if (chunkHash != 0)
            return null;
        return chunk;
    }

    @Override
    public void setChunk(int x, int z, Chunk chunk) {
        if (invalidXZ(x, z))
            return;
        this.chunk = chunk;
    }

    private boolean invalidXZ(int x, int z) {
        return x != 0 || z != 0;
    }

    @Override
    public int maxChunkX() {
        return 0;
    }

    @Override
    public int maxChunkZ() {
        return 0;
    }

    @Override
    public int minChunkX() {
        return 0;
    }

    @Override
    public int minChunkZ() {
        return 0;
    }

    @Override
    public World getWorld() {
        return world;
    }
}
