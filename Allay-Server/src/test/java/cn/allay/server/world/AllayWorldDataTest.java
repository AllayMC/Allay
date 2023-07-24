package cn.allay.server.world;

import cn.allay.api.math.vector.Vec3i;
import cn.allay.api.server.Server;
import cn.allay.api.server.ServerSettings;
import cn.allay.api.world.Difficulty;
import cn.allay.api.world.WorldData;
import cn.allay.server.world.storage.anvil.AnvilWorldStorage;
import cn.allay.testutils.AllayTestExtension;
import lombok.SneakyThrows;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * Allay Project 2023/5/31
 *
 * @author Cool_Loong
 */
@ExtendWith({AllayTestExtension.class, MockitoExtension.class})
public class AllayWorldDataTest {
    static Server server = Mockito.mock(Server.class);
    final AnvilWorldStorage je = new AnvilWorldStorage(Path.of("src/test/resources/jeworld"));
    final Path be = Path.of("src/test/resources/world/belevel.dat");

    @BeforeAll
    static void mockServerSettings() {
        @SuppressWarnings("resource") MockedStatic<Server> serve = Mockito.mockStatic(Server.class);
        serve.when(Server::getInstance).thenReturn(server);
        Mockito.when(server.getServerSettings()).thenReturn(ServerSettings
                .builder()
                .ip("0.0.0.0")
                .port(19132)
                .motd("Allay Server")
                .subMotd("Powered by Allay")
                .maxClientCount(20)
                .gameType(GameType.CREATIVE)
                .xboxAuth(false)
                .defaultTickingRadius(8)
                .defaultViewDistance(8)
                .build());
    }

    @SneakyThrows
    @Test
    void testLoadJeWorldData() {
        WorldData worldData = je.readWorldData();
        Assertions.assertEquals(Vec3i.of(-32, 68, 48), worldData.getSpawnPoint());
        Assertions.assertEquals(19133, worldData.getStorageVersion());
        Assertions.assertEquals(Difficulty.PEACEFUL, worldData.getDifficulty());
        Assertions.assertEquals(-8564372236162605245L, worldData.getRandomSeed());
    }

    @SneakyThrows
    @Test
    void testWriteJeWorldData() {
        WorldData worldData = je.readWorldData();
        Files.copy(Path.of("src/test/resources/jeworld/level.dat"), Path.of("src/test/resources/jeworld/level_old.dat"));
        je.writeWorldData(worldData);
        WorldData worldDataAllay = je.readWorldData();
        Assertions.assertTrue(worldDataAllay.isAllay());
    }

    @Test
    void testLoadBeWorldData() {
        //todo
    }

    @AfterAll
    static void end() {
        try {
            Path path = Path.of("src/test/resources/jeworld/level_old.dat");
            if (Files.exists(path)) {
                Files.copy(path, Path.of("src/test/resources/jeworld/level.dat"), StandardCopyOption.REPLACE_EXISTING);
                Files.delete(path);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
