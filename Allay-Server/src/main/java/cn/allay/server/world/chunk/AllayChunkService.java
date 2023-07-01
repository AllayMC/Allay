package cn.allay.server.world.chunk;

import cn.allay.api.utils.HashUtils;
import cn.allay.api.world.chunk.Chunk;
import cn.allay.api.world.chunk.ChunkService;
import cn.allay.api.world.generator.WorldGenerationService;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
public class AllayChunkService implements ChunkService {

    private final Map<Long, Chunk> loadedChunks = new Long2ObjectOpenHashMap<>();

    private final WorldGenerationService worldGenerationService;

    public AllayChunkService(WorldGenerationService worldGenerationService) {
        this.worldGenerationService = worldGenerationService;
    }

    @Override
    public void tick() {

    }

    @Override
    public @Nullable Chunk getChunk(int x, int z) {
        return loadedChunks.get(HashUtils.hashXZ(x, z));
    }

    @Override
    public void setChunk(int x, int z, Chunk chunk) {
        //TODO: need more works maybe
        loadedChunks.put(HashUtils.hashXZ(x, z), chunk);
    }

    @Override
    public int maxX() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int maxZ() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int minX() {
        return Integer.MIN_VALUE;
    }

    @Override
    public int minZ() {
        return Integer.MIN_VALUE;
    }
}
