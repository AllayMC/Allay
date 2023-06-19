package cn.allay.server.world;

import cn.allay.api.world.DimensionInfo;
import cn.allay.server.world.anvil.AnvilRegionFile;
import org.cloudburstmc.nbt.NbtMap;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Author: Cool_Loong <br>
 * Date: 6/7/2023 <br>
 * Allay Project
 */
public class AnvilRegionFileTest {
    final Path regionPath = Path.of("src/test/resources/world");

    @Test
    void loadMCA() throws IOException {
        AnvilRegionFile anvilRegionFile = new AnvilRegionFile(regionPath, 0, 0);
        NbtMap nbtMap = anvilRegionFile.readChunkData(0, 0);
        System.out.println(nbtMap.toSNBT(4));
    }
    @Test
    void writeMCA() throws IOException {
    }
}
