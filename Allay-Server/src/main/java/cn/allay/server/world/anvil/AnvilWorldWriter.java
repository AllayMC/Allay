package cn.allay.server.world.anvil;

import cn.allay.api.world.WorldData;
import cn.allay.api.world.WorldWriter;
import cn.allay.api.world.chunk.Chunk;

import java.io.File;
import java.util.concurrent.CompletableFuture;

/**
 * Author: Cool_Loong <br>
 * Date: 6/4/2023 <br>
 * Allay Project
 */
public class AnvilWorldWriter implements WorldWriter {
    private final File worldFolder;

    public AnvilWorldWriter(File worldFolder) {
        this.worldFolder = worldFolder;
    }

    @Override
    public CompletableFuture<Void> writeChunk(Chunk chunk) {
        return null;
    }

    @Override
    public void writeWorldData(WorldData worldData) {

    }
}
