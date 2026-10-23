package org.allaymc.server.world.storage.leveldb;

import lombok.SneakyThrows;
import org.allaymc.api.world.World;
import org.allaymc.api.world.biome.BiomeTypes;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.data.Difficulty;
import org.allaymc.api.world.dimension.DimensionTypes;
import org.allaymc.server.world.AllayWorldData;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;
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
 * {@code AllayLevelDBWorldStorageTest} exercises
 * world metadata and chunk persistence against a temporary LevelDB
 * directory.
 *
 * @author Cool_Loong
 */
@ExtendWith(AllayTestExtension.class)
class AllayLevelDBWorldStorageTest {

    /**
     * JUnit-managed temporary storage directory.
     */
    @TempDir
    Path tempDir;

    /**
     * World context for persistence tests.
     */
    World mockWorld;
    /**
     * {@code levelDBWorldStorage} holds storage opened for each test. Teardown
     * closes it before JUnit removes the temporary world directory.
     */
    AllayLevelDBWorldStorage levelDBWorldStorage;

    /**
     * Copies the metadata fixture and opens storage for a mock world before
     * each test. The mock supplies an empty player list and zero tick so
     * world-data operations have stable context.
     */
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

    /**
     * Closes the storage opened by test setup when a handle exists. Closing
     * runs after each test so the database releases resources before JUnit
     * removes the temporary world directory.
     */
    @AfterEach
    @SneakyThrows
    void tearDown() {
        if (levelDBWorldStorage != null) {
            levelDBWorldStorage.shutdown();
        }
    }

    /**
     * Copies an available classpath fixture beneath the temporary directory
     * after creating parent directories. A missing resource is silently left
     * uncopied, while directory creation and file-copy I/O failures propagate
     * to test setup.
     */
    private void copyTestResource(String resourcePath) throws IOException {
        Path targetPath = tempDir.resolve(resourcePath);
        Files.createDirectories(targetPath.getParent());
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            // A missing fixture stream leaves the destination absent rather
            // than failing this helper, allowing later setup or assertions to
            // expose the missing data.
            if (is != null) {
                Files.copy(is, targetPath);
            }
        }
    }

    /**
     * Loads the checked-in world metadata fixture through the LevelDB storage
     * reader. The restored spawn point, time of day, and difficulty must match
     * the fixture's recorded values.
     */
    @Test
    @SneakyThrows
    void testReadWorldData() {
        AllayWorldData worldData = (AllayWorldData) levelDBWorldStorage.readWorldData();
        worldData.setWorld(mockWorld);

        assertEquals(new Vector3i(0, 32767, 0), worldData.getSpawnPoint());
        assertEquals(48549, worldData.getTimeOfDay());
        assertEquals(Difficulty.EASY, worldData.getDifficulty());
    }

    /**
     * Changes a fixture world's spawn point and time before writing its
     * metadata. Reading the current file again must restore those changed
     * values; this case does not inspect the old-file copy.
     */
    @Test
    @SneakyThrows
    void testWriteWorldData() {
        AllayWorldData worldData = (AllayWorldData) levelDBWorldStorage.readWorldData();
        worldData.setWorld(mockWorld);
        worldData.setSpawnPoint(new Vector3i(1, 1, 1));
        worldData.setTimeOfDay(1234);

        levelDBWorldStorage.writeWorldData(worldData);

        AllayWorldData reloadedData = (AllayWorldData) levelDBWorldStorage.readWorldData();
        reloadedData.setWorld(mockWorld);
        assertEquals(new Vector3i(1, 1, 1), reloadedData.getSpawnPoint());
        assertEquals(1234, reloadedData.getTimeOfDay());
    }

    /**
     * Stores an overworld chunk filled with oak wood and forest biomes across
     * its vertical range. A synchronous reload must preserve sampled blocks,
     * biome identity, and the recorded height.
     */
    @Test
    @SneakyThrows
    void testWriteAndReadChunk() {
        AllayUnsafeChunk allayUnsafeChunk = AllayUnsafeChunk.builder().voidChunk(0, 0, DimensionTypes.OVERWORLD);
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

        Chunk chunk = levelDBWorldStorage.readChunkSync(0, 0, DimensionTypes.OVERWORLD);
        assertEquals(OAK_WOOD.getDefaultState(), chunk.getBlockState(0, 55, 0));
        assertEquals(BiomeTypes.FOREST, chunk.getBiome(0, 55, 0));
        assertEquals(319, chunk.getHeight(0, 0));
    }
}
