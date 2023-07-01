package cn.allay.api.world.generator;

import cn.allay.api.world.chunk.ChunkService;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
public interface WorldGenerationService {
    WorldGenerator getWorldGenerator();

    ChunkService getChunkService();

    <T extends LimitedWorldRegion> void submitGenerationTask(T limitedWorldRegion, FinishCallback<T> finishCallback);

    interface FinishCallback<U> {
        void onFinish(U limitedWorldRegion);
    }
}
