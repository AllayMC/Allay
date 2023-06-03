package cn.allay.server.world;

import cn.allay.api.world.WorldFormat;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Author: Cool_Loong <br>
 * Date: 5/31/2023 <br>
 * Allay Project
 */

public class AllayWorldDataTest {
    final Path je = Path.of("src/test/resources/leveldat/jelevel.dat");
    final Path be = Path.of("src/test/resources/leveldat/belevel.dat");

    @SneakyThrows
    @Test
    void testLoadJeWorldData() {
        AllayWorldData allayWorldData = new AllayWorldData(je.toFile(), WorldFormat.ANVIL);
        assertEquals(20400, allayWorldData.getNBT().getInt("WanderingTraderSpawnDelay"));
    }

    @SneakyThrows
    @Test
    void testLoadBeWorldData() {
        AllayWorldData allayWorldData = new AllayWorldData(be.toFile(), WorldFormat.LEVELDB);
        assertEquals(1684408747L, allayWorldData.getNBT().getLong("LastPlayed"));
    }
}
