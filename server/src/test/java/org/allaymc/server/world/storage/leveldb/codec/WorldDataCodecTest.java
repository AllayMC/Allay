package org.allaymc.server.world.storage.leveldb.codec;

import org.allaymc.api.world.WorldData;
import org.allaymc.api.world.data.Difficulty;
import org.allaymc.api.world.storage.WorldStorageException;
import org.allaymc.server.world.AllayWorldData;
import org.allaymc.testutils.AllayTestExtension;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.Vector3i;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AllayTestExtension.class)
class WorldDataCodecTest {

    @Test
    void testWriteAndReadRoundTrip() {
        var original = AllayWorldData.builder()
                .difficulty(Difficulty.HARD)
                .displayName("TestWorld")
                .spawnPoint(new Vector3i(10, 100, 20))
                .totalTime(5000L)
                .timeOfDay(6000)
                .worldStartCount(3L)
                .build();

        NbtMap nbt = WorldDataCodec.writeWorldDataToNBT(original);
        AllayWorldData restored = WorldDataCodec.readWorldDataFromNBT(nbt);

        assertEquals(Difficulty.HARD, restored.getDifficulty());
        assertEquals("TestWorld", restored.getDisplayName());
        assertEquals(new Vector3i(10, 100, 20), restored.getSpawnPoint());
        assertEquals(5000L, restored.getTotalTime());
        assertEquals(6000, restored.getTimeOfDay());
        assertEquals(3L, restored.getWorldStartCount());
    }

    @Test
    void testReadWithDefaultValues() {
        // Minimal NbtMap with only required version fields
        var nbt = NbtMap.builder()
                .putInt("StorageVersion", WorldDataCodec.CURRENT_STORAGE_VERSION)
                .build();

        AllayWorldData worldData = WorldDataCodec.readWorldDataFromNBT(nbt);

        assertEquals(WorldData.DEFAULT_WORLD_DISPLAY_NAME, worldData.getDisplayName());
        assertEquals(new Vector3i(0, 64, 0), worldData.getSpawnPoint());
        assertEquals(0L, worldData.getTotalTime());
        assertEquals(WorldData.TIME_SUNRISE, worldData.getTimeOfDay());
        assertEquals(0L, worldData.getWorldStartCount());
    }

    @Test
    void testUnsupportedStorageVersionThrows() {
        var nbt = NbtMap.builder()
                .putInt("StorageVersion", WorldDataCodec.CURRENT_STORAGE_VERSION + 1)
                .build();

        assertThrows(WorldStorageException.class, () -> WorldDataCodec.readWorldDataFromNBT(nbt));
    }
}
