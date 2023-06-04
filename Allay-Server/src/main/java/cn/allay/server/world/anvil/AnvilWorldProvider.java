package cn.allay.server.world.anvil;

import cn.allay.api.world.World;
import cn.allay.api.world.WorldProvider;
import cn.allay.api.world.WorldReader;
import cn.allay.api.world.WorldWriter;

import java.io.File;

/**
 * Author: Cool_Loong <br>
 * Date: 6/4/2023 <br>
 * Allay Project
 */
public class AnvilWorldProvider implements WorldProvider {
    private final File worldFolder;
    private final WorldReader worldReader;
    private final WorldWriter worldWriter;


    public AnvilWorldProvider(File worldFolder) {
        this.worldFolder = worldFolder;
        this.worldReader = new AnvilWorldReader(worldFolder);
        this.worldWriter = new AnvilWorldWriter(worldFolder);
    }

    @Override
    public File getWorldFolder() {
        return worldFolder;
    }

    @Override
    public WorldReader getChunkReader() {
        return worldReader;
    }

    @Override
    public WorldWriter getChunkWriter() {
        return worldWriter;
    }

    @Override
    public void close() {

    }
}
