package cn.allay.server.world.chunk;

import cn.allay.api.world.chunk.Chunk;
import cn.allay.api.world.generator.LimitedWorldRegion;
import org.jetbrains.annotations.Nullable;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
public class GenerationLimitedWorldRegion implements LimitedWorldRegion {

    private final Chunk[][] chunks = new Chunk[3][3];

    private final int centerChunkX;
    private final int centerChunkZ;

    public GenerationLimitedWorldRegion(int centerChunkX, int centerChunkZ) {
        this.centerChunkX = centerChunkX;
        this.centerChunkZ = centerChunkZ;
    }

    @Override
    public @Nullable Chunk getChunk(int x, int z) {
        if (invalidXZ(x, z))
            return null;
        return chunks[x + 1][z + 1];
    }

    @Override
    public void setChunk(int x, int z, Chunk chunk) {
        if (invalidXZ(x, z))
            return;
        chunks[x + 1][z + 1] = chunk;
    }


    @Override
    public int maxX() {
        return 1;
    }

    @Override
    public int maxZ() {
        return 1;
    }

    @Override
    public int minX() {
        return -1;
    }

    @Override
    public int minZ() {
        return -1;
    }

    private boolean invalidXZ(int x, int z) {
        return x < -1 || x > 1 || z < -1 || z > 1;
    }
}
