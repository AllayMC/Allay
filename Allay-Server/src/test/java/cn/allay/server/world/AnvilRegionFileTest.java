package cn.allay.server.world;

import cn.allay.server.world.storage.anvil.AnvilRegionFile;
import org.cloudburstmc.nbt.NbtMap;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Allay Project 2023/6/7
 *
 * @author Cool_Loong
 */
public class AnvilRegionFileTest {
    final Path regionPath = Path.of("src/test/resources/jeworld/region");

    @Test
    void loadMCA() throws IOException {
        AnvilRegionFile anvilRegionFile = new AnvilRegionFile(regionPath, -1, -1);
        NbtMap nbtMap = anvilRegionFile.readChunkData(31, 31);
        System.out.println(nbtMap.toSNBT(4));
    }
    @Test
    void writeMCA() throws IOException {
    }
}
