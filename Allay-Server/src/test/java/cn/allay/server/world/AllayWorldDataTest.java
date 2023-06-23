package cn.allay.server.world;

//import cn.allay.api.world.WorldFormat;
//import cn.allay.server.world.anvil.AllayAnvilWorldData;
//import cn.allay.server.world.leveldb.AllayLevelDBWorldData;
//import lombok.SneakyThrows;
//import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Cool_Loong <br>
 * @date 5/31/2023 <br>
 * Allay Project
 */

public class AllayWorldDataTest {
    final Path je = Path.of("src/test/resources/world/jelevel.dat");
    final Path be = Path.of("src/test/resources/world/belevel.dat");

    /*@SneakyThrows
    @Test
    void testLoadJeWorldData() {
        AllayAnvilWorldData allayWorldData = new AllayAnvilWorldData(je.toFile());
        assertEquals(20400, allayWorldData.getNBT().getInt("WanderingTraderSpawnDelay"));
    }

    @SneakyThrows
    @Test
    void testLoadBeWorldData() {
        AllayLevelDBWorldData allayWorldData = new AllayLevelDBWorldData(be.toFile());
        assertEquals(1684408747L, allayWorldData.getNBT().getLong("LastPlayed"));
    }*/
}
