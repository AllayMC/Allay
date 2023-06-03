package cn.allay.api.world;

import java.io.File;

/**
 * Author: Cool_Loong <br>
 * Date: 5/30/2023 <br>
 * Allay Project
 */
public interface WorldProvider {
    World getWorld();

    File getWorldFolder();

    WorldData getWorldData();
}
