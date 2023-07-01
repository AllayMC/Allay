package cn.allay.server.world.generator;

import cn.allay.api.world.chunk.ChunkService;
import cn.allay.api.world.generator.WorldGenerationService;
import cn.allay.api.world.generator.WorldGenerator;

import java.util.concurrent.ExecutorService;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
public class AllayWorldGenerationService implements WorldGenerationService {

    private final ExecutorService executorService;
    private final ChunkService chunkService;
    private final WorldGenerator worldGenerator;

    public AllayWorldGenerationService(ExecutorService executorService, ChunkService chunkService, WorldGenerator worldGenerator) {
        this.executorService = executorService;
        this.chunkService = chunkService;
        this.worldGenerator = worldGenerator;
    }

    @Override
    public WorldGenerator getWorldGenerator() {
        return worldGenerator;
    }
}
