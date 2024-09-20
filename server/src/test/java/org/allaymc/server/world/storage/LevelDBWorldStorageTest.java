package org.allaymc.server.world.storage;

import lombok.SneakyThrows;
import org.allaymc.api.eventbus.EventBus;
import org.allaymc.api.server.Server;
import org.allaymc.api.world.Difficulty;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.World;
import org.allaymc.api.world.WorldData;
import org.allaymc.api.world.biome.BiomeId;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.server.world.chunk.AllayChunk;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;
import org.allaymc.testutils.AllayTestExtension;
import org.apache.commons.io.FileUtils;
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
import java.util.List;
import java.util.concurrent.Executors;

import static org.allaymc.api.block.type.BlockTypes.OAK_WOOD;

/**
 * @author Cool_Loong
 */
@ExtendWith({AllayTestExtension.class, MockitoExtension.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LevelDBWorldStorageTest {
    // This is root path, relative to the `.run` directory
    static Path levelDat = Path.of("").toAbsolutePath().getParent();
    static Server server = Mockito.mock(Server.class);
    static EventBus eventBus = Mockito.mock(EventBus.class);
    static World mockWorld = Mockito.mock(World.class);
    static AllayLevelDBWorldStorage levelDBWorldStorage;

    @BeforeAll
    static void mockServerSettings() {
        try {
            Files.copy(levelDat.resolve("server/src/test/resources/beworld/level.dat"), levelDat.resolve("server/src/test/resources/beworld/copy/level.dat"), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        levelDBWorldStorage = new AllayLevelDBWorldStorage(levelDat.resolve("server/src/test/resources/beworld"));
        @SuppressWarnings("resource") MockedStatic<Server> serverClass = Mockito.mockStatic(Server.class);
        serverClass.when(Server::getInstance).thenReturn(server);
        Mockito.when(server.getEventBus()).thenReturn(eventBus);
        Mockito.when(server.getVirtualThreadPool()).thenReturn(Executors.newVirtualThreadPerTaskExecutor());
        Mockito.when(mockWorld.getPlayers()).thenReturn(List.of());
    }

    @AfterAll
    static void end() {
        try {
            levelDBWorldStorage.shutdown();
            Files.copy(levelDat.resolve("server/src/test/resources/beworld/copy/level.dat"), levelDat.resolve("server/src/test/resources/beworld/level.dat"), StandardCopyOption.REPLACE_EXISTING);
            FileUtils.deleteDirectory(levelDat.resolve("server/src/test/resources/beworld/db").toFile());
            Server.getInstance().shutdown();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    @Test
    @Order(1)
    void testLoadAllayWorldData() {
        WorldData worldData = levelDBWorldStorage.readWorldData();
        worldData.setWorld(mockWorld);
        Assertions.assertEquals(new Vector3i(0, 32767, 0), worldData.getSpawnPoint());
        Assertions.assertEquals(48549, worldData.getTime());
        Assertions.assertEquals(Difficulty.EASY, worldData.getDifficulty());
        Assertions.assertEquals(1811906518383890446L, worldData.getRandomSeed());
    }

    @SneakyThrows
    @Test
    @Order(2)
    void testWriteAllayWorldData() {
        WorldData worldData = levelDBWorldStorage.readWorldData();
        worldData.setWorld(mockWorld);
        worldData.setSpawnPoint(new Vector3i(1, 1, 1));
        worldData.setTime(1234);
        levelDBWorldStorage.writeWorldData(worldData);
        Assertions.assertEquals(new Vector3i(1, 1, 1), worldData.getSpawnPoint());
        Assertions.assertEquals(1234, worldData.getTime());
    }

    @SneakyThrows
    @Test
    @Order(3)
    void testSaveChunk() {
        AllayUnsafeChunk allayUnsafeChunk = AllayUnsafeChunk.builder().emptyChunk(0, 0, DimensionInfo.OVERWORLD);
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                for (int k = -64; k < 320; k++) {
                    allayUnsafeChunk.setBlockState(i, k, j, OAK_WOOD.getDefaultState());
                    allayUnsafeChunk.setBiome(i, k, j, BiomeId.FOREST);
                }
                allayUnsafeChunk.setHeight(i, j, 319);
            }
        }
        AllayChunk allayChunk = new AllayChunk(allayUnsafeChunk);
        levelDBWorldStorage.writeChunk(allayChunk).join();
    }

    @SneakyThrows
    @Test
    @Order(4)
    void testReadChunk() {
        Chunk chunk = levelDBWorldStorage.readChunk(0, 0, DimensionInfo.OVERWORLD).join();
        Assertions.assertEquals(OAK_WOOD.getDefaultState(), chunk.getBlockState(0, 55, 0));
        Assertions.assertEquals(BiomeId.FOREST, chunk.getBiome(0, 55, 0));
        Assertions.assertEquals(319, chunk.getHeight(0, 0));
    }
}
