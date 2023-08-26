package cn.allay.server.world;

import cn.allay.api.data.VanillaBiomeId;
import cn.allay.api.data.VanillaBlockTypes;
import cn.allay.api.server.Server;
import cn.allay.api.server.ServerSettings;
import cn.allay.api.world.Difficulty;
import cn.allay.api.world.DimensionInfo;
import cn.allay.api.world.WorldData;
import cn.allay.api.world.chunk.Chunk;
import cn.allay.server.world.chunk.AllayChunk;
import cn.allay.server.world.chunk.AllayUnsafeChunk;
import cn.allay.server.world.storage.rocksdb.RocksDBWorldStorage;
import cn.allay.testutils.AllayTestExtension;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.joml.Vector3i;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.Executors;

/**
 * Allay Project 2023/5/31
 *
 * @author Cool_Loong
 */
@ExtendWith({AllayTestExtension.class, MockitoExtension.class})
public class RocksDBWorldStorageTest {
    static Server server = Mockito.mock(Server.class);
    final Path be = Path.of("src/test/resources/world/belevel.dat");

    @BeforeAll
    static void mockServerSettings() {
        @SuppressWarnings("resource") MockedStatic<Server> serve = Mockito.mockStatic(Server.class);
        serve.when(Server::getInstance).thenReturn(server);
        Mockito.when(server.getVirtualThreadPool()).thenReturn(Executors.newVirtualThreadPerTaskExecutor());
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
    @Order(1)
    void testLoadAllayWorldData() {
        try (final RocksDBWorldStorage allay = new RocksDBWorldStorage(Path.of("src/test/resources/allayworld"))) {
            WorldData worldData = allay.readWorldData();
            Assertions.assertEquals(new Vector3i(32, 86, -32), worldData.getSpawnPoint());
            Assertions.assertEquals(6974, worldData.getTime());
            Assertions.assertEquals(Difficulty.PEACEFUL, worldData.getDifficulty());
            Assertions.assertEquals(2348465307070870700L, worldData.getRandomSeed());
        }
    }

    @SneakyThrows
    @Test
    @Order(2)
    void testWriteAllayWorldData() {
        try (final RocksDBWorldStorage allay = new RocksDBWorldStorage(Path.of("src/test/resources/allayworld/write"))) {
            WorldData worldData = allay.readWorldData();
            worldData.setSpawnPoint(new Vector3i(1, 1, 1));
            worldData.setTime(1234);
            allay.writeWorldData(worldData);
            Assertions.assertEquals(new Vector3i(1, 1, 1), worldData.getSpawnPoint());
            Assertions.assertEquals(1234, worldData.getTime());
        }
    }

    @SneakyThrows
    @Test
    @Order(3)
    void testSaveChunk() {
        AllayUnsafeChunk allayUnsafeChunk = AllayUnsafeChunk.builder().emptyChunk(0, 0, DimensionInfo.OVERWORLD);
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                for (int k = -64; k < 320; k++) {
                    allayUnsafeChunk.setBlockState(i, k, j, VanillaBlockTypes.WOOD_TYPE.getDefaultState());
                    allayUnsafeChunk.setBiome(i, k, j, VanillaBiomeId.FOREST);
                }
                allayUnsafeChunk.setHeight(i, j, 319);
            }
        }
        AllayChunk allayChunk = new AllayChunk(allayUnsafeChunk);
        try (final RocksDBWorldStorage allay = new RocksDBWorldStorage(Path.of("src/test/resources/allayworld"))) {
            allay.writeChunk(allayChunk).get();
        }
    }

    @SneakyThrows
    @Test
    @Order(4)
    void testReadChunk() {
        try (final RocksDBWorldStorage allay = new RocksDBWorldStorage(Path.of("src/test/resources/allayworld"))) {
            Chunk chunk = allay.readChunk(0, 0).get();
            Assertions.assertEquals(VanillaBlockTypes.WOOD_TYPE.getDefaultState(), chunk.getBlockState(0, 55, 0));
            Assertions.assertEquals(VanillaBiomeId.FOREST, chunk.getBiome(0, 55, 0));
            Assertions.assertEquals(319, chunk.getHeight(0, 0));
        }
    }

    @AfterAll
    static void end() {
        try {
            Files.copy(Path.of("src/test/resources/allayworld/level.dat"), Path.of("src/test/resources/allayworld/write/level.dat"), StandardCopyOption.REPLACE_EXISTING);
            FileUtils.deleteDirectory(Path.of("src/test/resources/allayworld/db").toFile());
            FileUtils.deleteDirectory(Path.of("src/test/resources/allayworld/write/db").toFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
