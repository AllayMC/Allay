package cn.allay.server.world.generator;

import cn.allay.api.world.generator.LimitedWorldRegion;
import cn.allay.api.world.generator.WorldGenerationService;
import cn.allay.api.world.generator.WorldGenerator;
import lombok.Getter;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
public class AllayWorldGenerationService implements WorldGenerationService {

    private final ForkJoinPool executorService;
    @Getter
    private final WorldGenerator worldGenerator;

    public AllayWorldGenerationService(ForkJoinPool executorService, WorldGenerator worldGenerator) {
        this.executorService = executorService;
        this.worldGenerator = worldGenerator;
    }

    public <T extends LimitedWorldRegion> void submitGenerationTask(T limitedWorldRegion, FinishCallback<T> finishCallback) {
        WorldGenerationTask<T> worldGenerationTask = new WorldGenerationTask<>(limitedWorldRegion, finishCallback);
        executorService.submit(worldGenerationTask);
    }

    public class WorldGenerationTask<T extends LimitedWorldRegion> extends RecursiveAction {

        private final T limitedWorldRegion;
        private final FinishCallback<T> finishCallback;

        public WorldGenerationTask(T limitedWorldRegion, FinishCallback<T> finishCallback) {
            this.limitedWorldRegion = limitedWorldRegion;
            this.finishCallback = finishCallback;
        }

        @Override
        protected void compute() {
            worldGenerator.generate(limitedWorldRegion);
            finishCallback.onFinish(limitedWorldRegion);
        }
    }
}
