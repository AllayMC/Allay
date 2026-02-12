package org.allaymc.server.world.storage;

import lombok.SneakyThrows;
import org.allaymc.api.world.World;
import org.allaymc.api.world.biome.BiomeTypes;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.data.Difficulty;
import org.allaymc.api.world.data.DimensionInfo;
import org.allaymc.server.world.AllayWorldData;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;
import org.allaymc.server.world.storage.leveldb.AllayLevelDBWorldStorage;
import org.allaymc.testutils.AllayTestExtension;
import org.joml.Vector3i;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.allaymc.api.block.type.BlockTypes.OAK_WOOD;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Cool_Loong
 */
@ExtendWith(AllayTestExtension.class)
class LevelDBWorldStorageTest {

    @TempDir
    Path tempDir;

    World mockWorld;
    AllayLevelDBWorldStorage levelDBWorldStorage;

    @BeforeEach
    @SneakyThrows
    void setUp() {
        copyTestResource("ldbworld/level.dat");
        mockWorld = Mockito.mock(World.class);
        Mockito.when(mockWorld.getPlayers()).thenReturn(List.of());
        Mockito.when(mockWorld.getTick()).thenReturn(0L);
        levelDBWorldStorage = new AllayLevelDBWorldStorage(tempDir.resolve("ldbworld"));
        levelDBWorldStorage.setWorld(mockWorld);
    }

    @AfterEach
    @SneakyThrows
    void tearDown() {
        if (levelDBWorldStorage != null) {
            levelDBWorldStorage.shutdown();
        }
    }

    private void copyTestResource(String resourcePath) throws IOException {
        Path targetPath = tempDir.resolve(resourcePath);
        Files.createDirectories(targetPath.getParent());
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (is != null) {
                Files.copy(is, targetPath);
            }
        }
    }

    @Test
    @SneakyThrows
    void testReadWorldData() {
        AllayWorldData worldData = (AllayWorldData) levelDBWorldStorage.readWorldData();
        worldData.setWorld(mockWorld);

        assertEquals(new Vector3i(0, 32767, 0), worldData.getSpawnPoint());
        assertEquals(48549, worldData.getTimeOfDay());
        assertEquals(Difficulty.EASY, worldData.getDifficulty());
    }

    @Test
    @SneakyThrows
    void testWriteWorldData() {
        AllayWorldData worldData = (AllayWorldData) levelDBWorldStorage.readWorldData();
        worldData.setWorld(mockWorld);
        worldData.setSpawnPoint(new Vector3i(1, 1, 1));
        worldData.setTimeOfDay(1234);

        levelDBWorldStorage.writeWorldData(worldData);

        // Re-read to verify persistence
        AllayWorldData reloadedData = (AllayWorldData) levelDBWorldStorage.readWorldData();
        reloadedData.setWorld(mockWorld);
        assertEquals(new Vector3i(1, 1, 1), reloadedData.getSpawnPoint());
        assertEquals(1234, reloadedData.getTimeOfDay());
    }

    @Test
    @SneakyThrows
    void testWriteAndReadChunk() {
        AllayUnsafeChunk allayUnsafeChunk = AllayUnsafeChunk.builder().voidChunk(0, 0, DimensionInfo.OVERWORLD);
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                for (int k = -64; k < 320; k++) {
                    allayUnsafeChunk.setBlockState(i, k, j, OAK_WOOD.getDefaultState());
                    allayUnsafeChunk.setBiome(i, k, j, BiomeTypes.FOREST);
                }
                allayUnsafeChunk.setHeight(i, j, (short) 319);
            }
        }

        levelDBWorldStorage.writeChunkSync(allayUnsafeChunk.toSafeChunk());

        Chunk chunk = levelDBWorldStorage.readChunkSync(0, 0, DimensionInfo.OVERWORLD);
        assertEquals(OAK_WOOD.getDefaultState(), chunk.getBlockState(0, 55, 0));
        assertEquals(BiomeTypes.FOREST, chunk.getBiome(0, 55, 0));
        assertEquals(319, chunk.getHeight(0, 0));
    }
}
