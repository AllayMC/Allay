package org.allaymc.server;

import org.allaymc.api.MissingImplementationException;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.Weather;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;
import org.allaymc.server.world.service.AllayLightService;
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
public class LightServiceJMHTest {

    private AllayLightService lightService;

    @Setup
    public void setup() throws MissingImplementationException {
        Allay.initI18n();
        Allay.initAllay();
        lightService = new AllayLightService(DimensionInfo.THE_END, "test_world", () -> true, () -> 0, () -> Set.of(Weather.CLEAR));
        for (int x = -3; x <= 3; x++) {
            for (int z = -3; z <= 3; z++) {
                lightService.onChunkLoad(
                        AllayUnsafeChunk
                                .builder()
                                // Use end dimension to disable skylight
                                .voidChunk(x, z, DimensionInfo.THE_END)
                                .toSafeChunk()
                );
            }
        }
        lightService.handleUpdateUninterruptedlyInAllQueues();
        for (int x = -3 * 16; x <= 3 * 16; x++) {
            for (int z = -3 * 16; z <= 3 * 16; z++) {
                lightService.onBlockChange(x, 0, z, 0, 15);
            }
        }
        lightService.handleUpdateUninterruptedlyInAllQueues();
    }

    @Benchmark
    public void testPlaceAndRemoveLight() {
        lightService.onBlockChange(0, 1, 0, 15, 0);
        lightService.onBlockChange(0, 1, 0, 0, 0);
        lightService.handleUpdateUninterruptedlyInAllQueues();
    }
}
