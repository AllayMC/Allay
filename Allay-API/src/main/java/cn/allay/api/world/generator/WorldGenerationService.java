package cn.allay.api.world.generator;

import java.util.concurrent.ForkJoinTask;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
public interface WorldGenerationService {
    WorldGenerator getWorldGenerator();

    <T extends LimitedWorldRegion> ForkJoinTask<Void> submitGenerationTask(T limitedWorldRegion, FinishCallback<T> finishCallback);

    interface FinishCallback<U> {
        void onFinish(U limitedWorldRegion);
    }
}
