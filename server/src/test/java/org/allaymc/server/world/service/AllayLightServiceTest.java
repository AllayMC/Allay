package org.allaymc.server.world.service;

import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.Weather;
import org.allaymc.api.world.WorldData;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
class AllayLightServiceTest {

    @Test
    void testBlockLight() {
        var lightService = new AllayLightService(DimensionInfo.OVERWORLD, "test_world", () -> WorldData.TIME_NOON, () -> Set.of(Weather.CLEAR));

        // Prepare space for testing
        for (int x = -3; x <= 3; x++) {
            for (int z = -3; z <= 3; z++) {
                lightService.onChunkLoad(
                        AllayUnsafeChunk
                                .builder()
                                .voidChunk(x, z, DimensionInfo.OVERWORLD)
                                .toSafeChunk()
                );
            }
        }
        lightService.handleUpdateInAllQueues();

        // Case 1
        lightService.onBlockChange(0, 1, 0, 14, 0);
        lightService.handleUpdateInAllQueues();
        assertEquals(14, lightService.getBlockLight(0, 1, 0));
        assertEquals(13, lightService.getBlockLight(1, 1, 0));
        assertEquals(13, lightService.getBlockLight(0, 1, 1));
        assertEquals(13, lightService.getBlockLight(-1, 1, 0));
        assertEquals(13, lightService.getBlockLight(0, 1, -1));
        assertEquals(13, lightService.getBlockLight(0, 2, 0));

        // Case 2
        lightService.onBlockChange(2, 1, 0, 0, 15);
        lightService.handleUpdateInAllQueues();
        assertEquals(0, lightService.getBlockLight(2, 1, 0));
        assertEquals(9, lightService.getBlockLight(3, 1, 0));

        // Case 3
        lightService.onBlockChange(0, 1, 0, 0, 0);
        lightService.handleUpdateInAllQueues();
        assertEquals(0, lightService.getBlockLight(0, 1, 0));
        assertEquals(0, lightService.getBlockLight(1, 1, 0));
        assertEquals(0, lightService.getBlockLight(0, 1, 1));
        assertEquals(0, lightService.getBlockLight(-1, 1, 0));
        assertEquals(0, lightService.getBlockLight(0, 1, -1));
        assertEquals(0, lightService.getBlockLight(0, 2, 0));

        lightService.shutdown();
    }

    @Test
    void testSkyLight() {
        var lightService = new AllayLightService(DimensionInfo.OVERWORLD, "test_world", () -> WorldData.TIME_NOON, () -> Set.of(Weather.CLEAR));

        // Prepare space for testing
        for (int x = -3; x <= 3; x++) {
            for (int z = -3; z <= 3; z++) {
                lightService.onChunkLoad(
                        AllayUnsafeChunk
                                .builder()
                                .voidChunk(x, z, DimensionInfo.OVERWORLD)
                                .toSafeChunk()
                );
            }
        }
        lightService.handleUpdateInAllQueues();

        // Case 1
        lightService.onBlockChange(0, 0, 0, 0, 15);
        lightService.handleUpdateInAllQueues();
        assertEquals(15, lightService.getSkyLight(0, 1, 0));
        assertEquals(15, lightService.getSkyLight(0, 2, 0));
        assertEquals(15, lightService.getSkyLight(0, 100, 0));
        assertEquals(15, lightService.getSkyLight(0, 200, 0));

        // Case 2
        lightService.onBlockChange(0, 0, 0, 0, 0);
        lightService.handleUpdateInAllQueues();
        assertEquals(15, lightService.getSkyLight(0, -1, 0));

        // Case 3
        lightService.onBlockChange(0, 0, 0, 0, 1);
        lightService.handleUpdateInAllQueues();
        assertEquals(14, lightService.getSkyLight(0, 0, 0));
        assertEquals(14, lightService.getSkyLight(0, -1, 0));
        assertEquals(14, lightService.getSkyLight(0, -2, 0));
        assertEquals(14, lightService.getSkyLight(0, -3, 0));

        // Case 4
        lightService.onBlockChange(0, 0, 0, 0, 15);
        lightService.onBlockChange(0, 1, 0, 0, 15);
        lightService.onBlockChange(0, 2, 0, 0, 15);
        lightService.onBlockChange(0, 1, 0, 0, 0);
        lightService.handleUpdateInAllQueues();
        assertEquals(14, lightService.getSkyLight(0, 1, 0));
        lightService.onBlockChange(0, 0, 0, 0, 0);
        lightService.onBlockChange(0, 2, 0, 0, 0);

        // Case 5
        lightService.onBlockChange(0, 0, 0, 0, 15);
        lightService.onBlockChange(1, 1, 0, 0, 15);
        lightService.onBlockChange(0, 1, 1, 0, 15);
        lightService.onBlockChange(-1, 1, 0, 0, 15);
        lightService.onBlockChange(0, 1, -1, 0, 15);
        lightService.onBlockChange(0, 2, 0, 0, 15);
        lightService.handleUpdateInAllQueues();
        assertEquals(0, lightService.getSkyLight(0, 1, 0));

        lightService.shutdown();
    }

    @Test
    void testCalculateSkylightReduction() {
        assertEquals(11, AllayLightService.calculateSkylightReduction(13670, Set.of(Weather.CLEAR)));
        assertEquals(11, AllayLightService.calculateSkylightReduction(22330, Set.of(Weather.CLEAR)));
        assertEquals(11, AllayLightService.calculateSkylightReduction(13670, Set.of(Weather.RAIN)));
        assertEquals(11, AllayLightService.calculateSkylightReduction(22330, Set.of(Weather.RAIN)));
        assertEquals(11, AllayLightService.calculateSkylightReduction(13670, Set.of(Weather.RAIN, Weather.THUNDER)));
        assertEquals(11, AllayLightService.calculateSkylightReduction(22330, Set.of(Weather.RAIN, Weather.THUNDER)));

        assertEquals(10, AllayLightService.calculateSkylightReduction(22331, Set.of(Weather.CLEAR)));
        assertEquals(10, AllayLightService.calculateSkylightReduction(22491, Set.of(Weather.CLEAR)));
        assertEquals(10, AllayLightService.calculateSkylightReduction(13509, Set.of(Weather.CLEAR)));
        assertEquals(10, AllayLightService.calculateSkylightReduction(13669, Set.of(Weather.CLEAR)));
        assertEquals(10, AllayLightService.calculateSkylightReduction(22331, Set.of(Weather.RAIN)));
        assertEquals(10, AllayLightService.calculateSkylightReduction(22565, Set.of(Weather.RAIN)));
        assertEquals(10, AllayLightService.calculateSkylightReduction(13436, Set.of(Weather.RAIN)));
        assertEquals(10, AllayLightService.calculateSkylightReduction(13669, Set.of(Weather.RAIN)));
        assertEquals(10, AllayLightService.calculateSkylightReduction(22331, Set.of(Weather.RAIN, Weather.THUNDER)));
        assertEquals(10, AllayLightService.calculateSkylightReduction(22671, Set.of(Weather.RAIN, Weather.THUNDER)));
        assertEquals(10, AllayLightService.calculateSkylightReduction(13330, Set.of(Weather.RAIN, Weather.THUNDER)));
        assertEquals(10, AllayLightService.calculateSkylightReduction(13669, Set.of(Weather.RAIN, Weather.THUNDER)));
    }

    @Test
    void testLightDataPacking() {
        for (int a = 0; a < 16; a++) {
            for (int b = 0; b < 16; b++) {
                testLightDataPacking(a, b);
            }
        }
        assertThrows(IllegalArgumentException.class, () -> AllayLightService.packLightData(16, 0));
        assertThrows(IllegalArgumentException.class, () -> AllayLightService.packLightData(0, 16));
    }

    void testLightDataPacking(int a, int b) {
        var data = AllayLightService.packLightData(a, b);
        assertEquals(a, AllayLightService.unpackLightEmission(data));
        assertEquals(b, AllayLightService.unpackLightDampening(data));
    }
}