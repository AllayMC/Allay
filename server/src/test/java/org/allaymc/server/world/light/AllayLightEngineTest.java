package org.allaymc.server.world.light;

import org.allaymc.api.world.WorldData;
import org.allaymc.api.world.data.Weather;
import org.allaymc.api.world.dimension.DimensionTypes;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * {@code AllayLightEngineTest} checks propagation, environmental skylight
 * reduction, and packed light properties using synchronously drained update
 * queues.
 *
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
class AllayLightEngineTest {

    /**
     * Loads neighboring void chunks and changes an emitter and an obstacle.
     * The assertions check outward attenuation, illumination around the
     * obstacle, and removal of the source's contribution.
     */
    @Test
    void testBlockLight() {
        var lightService = new AllayLightEngine(DimensionTypes.OVERWORLD, "test_world", () -> WorldData.TIME_NOON, () -> Weather.CLEAR);

        // Load a surrounding region before draining updates so central chunks
        // meet the light engine's neighbor availability requirement for propagation.
        for (int x = -3; x <= 3; x++) {
            for (int z = -3; z <= 3; z++) {
                lightService.onChunkLoad(
                        AllayUnsafeChunk
                                .builder()
                                .voidChunk(x, z, DimensionTypes.OVERWORLD)
                                .toSafeChunk()
                );
            }
        }
        lightService.handleUpdateInAllQueues();

        lightService.onBlockChange(0, 1, 0, 14, 0);
        lightService.handleUpdateInAllQueues();
        assertEquals(14, lightService.getBlockLight(0, 1, 0));
        assertEquals(13, lightService.getBlockLight(1, 1, 0));
        assertEquals(13, lightService.getBlockLight(0, 1, 1));
        assertEquals(13, lightService.getBlockLight(-1, 1, 0));
        assertEquals(13, lightService.getBlockLight(0, 1, -1));
        assertEquals(13, lightService.getBlockLight(0, 2, 0));

        lightService.onBlockChange(2, 1, 0, 0, 15);
        lightService.handleUpdateInAllQueues();
        assertEquals(0, lightService.getBlockLight(2, 1, 0));
        assertEquals(9, lightService.getBlockLight(3, 1, 0));

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

    /**
     * Loads neighboring void chunks and changes blocks around a sky-exposed
     * column. The assertions check source height changes, attenuation below a
     * weak obstacle, and complete enclosure of a position.
     */
    @Test
    void testSkyLight() {
        var lightService = new AllayLightEngine(DimensionTypes.OVERWORLD, "test_world", () -> WorldData.TIME_NOON, () -> Weather.CLEAR);

        // Load a surrounding region before draining updates so central chunks
        // meet the light engine's neighbor availability requirement for propagation.
        for (int x = -3; x <= 3; x++) {
            for (int z = -3; z <= 3; z++) {
                lightService.onChunkLoad(
                        AllayUnsafeChunk
                                .builder()
                                .voidChunk(x, z, DimensionTypes.OVERWORLD)
                                .toSafeChunk()
                );
            }
        }
        lightService.handleUpdateInAllQueues();

        lightService.onBlockChange(0, 0, 0, 0, 15);
        lightService.handleUpdateInAllQueues();
        assertEquals(15, lightService.getSkyLight(0, 1, 0));
        assertEquals(15, lightService.getSkyLight(0, 2, 0));
        assertEquals(15, lightService.getSkyLight(0, 100, 0));
        assertEquals(15, lightService.getSkyLight(0, 200, 0));

        lightService.onBlockChange(0, 0, 0, 0, 0);
        lightService.handleUpdateInAllQueues();
        assertEquals(15, lightService.getSkyLight(0, -1, 0));

        lightService.onBlockChange(0, 0, 0, 0, 1);
        lightService.handleUpdateInAllQueues();
        assertEquals(14, lightService.getSkyLight(0, 0, 0));
        assertEquals(14, lightService.getSkyLight(0, -1, 0));
        assertEquals(14, lightService.getSkyLight(0, -2, 0));
        assertEquals(14, lightService.getSkyLight(0, -3, 0));

        // Change multiple heights before draining so queued source removals interact.
        lightService.onBlockChange(0, 0, 0, 0, 15);
        lightService.onBlockChange(0, 1, 0, 0, 15);
        lightService.onBlockChange(0, 2, 0, 0, 15);
        lightService.onBlockChange(0, 1, 0, 0, 0);
        lightService.handleUpdateInAllQueues();
        assertEquals(14, lightService.getSkyLight(0, 1, 0));
        lightService.onBlockChange(0, 0, 0, 0, 0);
        lightService.onBlockChange(0, 2, 0, 0, 0);

        // Enclose the sampled position on all six faces to remove its incoming skylight.
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

    /**
     * Samples night and transition times under clear, rainy, and thunder
     * weather. The assertions protect the expected integer reduction around
     * the tested boundaries for each environmental state.
     */
    @Test
    void testCalculateSkylightReduction() {
        assertEquals(11, AllayLightEngine.calculateSkylightReduction(13670, Weather.CLEAR));
        assertEquals(11, AllayLightEngine.calculateSkylightReduction(22330, Weather.CLEAR));
        assertEquals(11, AllayLightEngine.calculateSkylightReduction(13670, Weather.RAIN));
        assertEquals(11, AllayLightEngine.calculateSkylightReduction(22330, Weather.RAIN));
        assertEquals(11, AllayLightEngine.calculateSkylightReduction(13670, Weather.THUNDER));
        assertEquals(11, AllayLightEngine.calculateSkylightReduction(22330, Weather.THUNDER));

        assertEquals(10, AllayLightEngine.calculateSkylightReduction(22331, Weather.CLEAR));
        assertEquals(10, AllayLightEngine.calculateSkylightReduction(22491, Weather.CLEAR));
        assertEquals(10, AllayLightEngine.calculateSkylightReduction(13509, Weather.CLEAR));
        assertEquals(10, AllayLightEngine.calculateSkylightReduction(13669, Weather.CLEAR));
        assertEquals(10, AllayLightEngine.calculateSkylightReduction(22331, Weather.RAIN));
        assertEquals(10, AllayLightEngine.calculateSkylightReduction(22565, Weather.RAIN));
        assertEquals(10, AllayLightEngine.calculateSkylightReduction(13436, Weather.RAIN));
        assertEquals(10, AllayLightEngine.calculateSkylightReduction(13669, Weather.RAIN));
        assertEquals(10, AllayLightEngine.calculateSkylightReduction(22331, Weather.THUNDER));
        assertEquals(10, AllayLightEngine.calculateSkylightReduction(22671, Weather.THUNDER));
        assertEquals(10, AllayLightEngine.calculateSkylightReduction(13330, Weather.THUNDER));
        assertEquals(10, AllayLightEngine.calculateSkylightReduction(13669, Weather.THUNDER));
    }

    /**
     * Exercises every pair of four-bit emission and dampening values. The test
     * checks that both components round-trip and that either component
     * reaching sixteen is rejected by the packer.
     */
    @Test
    void testLightDataPacking() {
        for (int a = 0; a < 16; a++) {
            for (int b = 0; b < 16; b++) {
                testLightDataPacking(a, b);
            }
        }
        assertThrows(IllegalArgumentException.class, () -> AllayLightEngine.packLightData(16, 0));
        assertThrows(IllegalArgumentException.class, () -> AllayLightEngine.packLightData(0, 16));
    }

    /**
     * Checks that packing preserves the supplied emission and dampening
     * components.
     */
    void testLightDataPacking(int a, int b) {
        var data = AllayLightEngine.packLightData(a, b);
        assertEquals(a, AllayLightEngine.unpackLightEmission(data));
        assertEquals(b, AllayLightEngine.unpackLightDampening(data));
    }
}
