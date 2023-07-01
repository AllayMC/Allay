package cn.allay.server.world.generator;

import cn.allay.api.world.generator.WorldGenerationService;

import java.util.concurrent.ExecutorService;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
public class AllayWorldGenerationService implements WorldGenerationService {

    private ExecutorService executorService;

    public AllayWorldGenerationService(ExecutorService executorService) {
        this.executorService = executorService;
    }
}
