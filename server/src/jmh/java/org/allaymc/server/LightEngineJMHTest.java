package org.allaymc.server;

import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.data.Weather;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;
import org.allaymc.server.world.light.AllayLightEngine;
import org.openjdk.jmh.annotations.*;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author daoge_cmd
 */
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class LightEngineJMHTest {

    private AllayLightEngine lightEngine;

    @Setup
    public void setup() {
        Allay.initI18n();
        Allay.initAllay();
        lightEngine = new AllayLightEngine(DimensionInfo.THE_END, "test_world", () -> 0, () -> Set.of(Weather.CLEAR));
        for (int x = -3; x <= 3; x++) {
            for (int z = -3; z <= 3; z++) {
                lightEngine.onChunkLoad(
                        AllayUnsafeChunk
                                .builder()
                                // Use end dimension to disable skylight
                                .voidChunk(x, z, DimensionInfo.THE_END)
                                .toSafeChunk()
                );
            }
        }
        lightEngine.handleUpdateInAllQueues();
        for (int x = -3 * 16; x <= 3 * 16; x++) {
            for (int z = -3 * 16; z <= 3 * 16; z++) {
                lightEngine.onBlockChange(x, 0, z, 0, 15);
            }
        }
        lightEngine.handleUpdateInAllQueues();
    }

    @Benchmark
    public void testPlaceAndRemoveLight() {
        lightEngine.onBlockChange(0, 1, 0, 15, 0);
        lightEngine.onBlockChange(0, 1, 0, 0, 0);
        lightEngine.handleUpdateInAllQueues();
    }
}
