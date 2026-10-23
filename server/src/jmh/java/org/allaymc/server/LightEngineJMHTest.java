package org.allaymc.server;

import org.allaymc.api.world.data.Weather;
import org.allaymc.api.world.dimension.DimensionTypes;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;
import org.allaymc.server.world.light.AllayLightEngine;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * {@code LightEngineJMHTest} measures repeated light-source placement and
 * removal against preloaded End chunks. The End dimension disables skylight,
 * and setup establishes a zero-emission, full-dampening substrate plane before
 * the measured operation queues source changes and drains pending lighting
 * work.
 *
 * @author daoge_cmd
 */
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class LightEngineJMHTest {

    /**
     * {@code lightEngine} retains the benchmark's mutable lighting state.
     * {@code setup} initializes it once per trial, and the single benchmark thread
     * reuses it across invocations.
     */
    private AllayLightEngine lightEngine;

    /**
     * Initializes runtime registries and a seven-by-seven End chunk lighting
     * region before measurement. The setup applies zero emission and maximum
     * dampening across a substrate plane at height zero, then drains chunk
     * loading and material updates outside the measured method.
     */
    @Setup
    public void setup() {
        Allay.initI18n();
        Allay.initAllay();
        lightEngine = new AllayLightEngine(DimensionTypes.THE_END, "test_world", () -> 0, () -> Weather.CLEAR);
        // Seven chunks per axis supply neighbors around the measured center,
        // keeping initial chunk loading outside the timed lighting operation.
        for (int x = -3; x <= 3; x++) {
            for (int z = -3; z <= 3; z++) {
                lightEngine.onChunkLoad(
                        AllayUnsafeChunk
                                .builder()
                                // End chunks disable skylight so the benchmark measures
                                // the prepared block-light workload alone.
                                .voidChunk(x, z, DimensionTypes.THE_END)
                                .toSafeChunk()
                );
            }
        }
        lightEngine.handleUpdateInAllQueues();
        // Sixteen-block chunk widths convert the prepared radius to block
        // coordinates for the unmeasured substrate material initialization.
        for (int x = -3 * 16; x <= 3 * 16; x++) {
            for (int z = -3 * 16; z <= 3 * 16; z++) {
                lightEngine.onBlockChange(x, 0, z, 0, 15);
            }
        }
        lightEngine.handleUpdateInAllQueues();
    }

    /**
     * Measures enqueuing maximum emission followed by zero emission at one
     * position and then draining lighting queues. The same initialized engine
     * serves every invocation, excluding server setup, chunk loading, and
     * substrate preparation from the measured operation.
     */
    @Benchmark
    public void testPlaceAndRemoveLight() {
        lightEngine.onBlockChange(0, 1, 0, 15, 0);
        lightEngine.onBlockChange(0, 1, 0, 0, 0);
        lightEngine.handleUpdateInAllQueues();
    }
}
