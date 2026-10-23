package org.allaymc.server;

import org.allaymc.api.block.property.enums.MinecraftFacingDirection;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * {@code BlockStateUpdateJMHTest} measures repeated observer-state property updates
 * after server initialization.
 *
 * @author Cool_Loong
 */
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class BlockStateUpdateJMHTest {
    /**
     * {@code observer} holds the latest canonical state produced by a benchmark
     * invocation. Benchmark scope shares this field, while the configured single
     * thread performs its updates.
     */
    private BlockState observer;

    /**
     * Initializes server registries and selects the observer default state before
     * measurements. The benchmark methods reuse this field so their measured work
     * excludes initial server startup and registry construction.
     */
    @Setup
    public void init() {
        Allay.initI18n();
        Allay.initAllay();
        observer = BlockTypes.OBSERVER.getDefaultState();
    }

    /**
     * Measures one observer-facing update using an already initialized state. The
     * result replaces {@code observer}, so later invocations also measure lookup
     * when the requested facing is already set.
     */
    @Benchmark
    public void test1() {
        observer = observer.setPropertyValue(BlockPropertyTypes.MINECRAFT_FACING_DIRECTION, MinecraftFacingDirection.UP);
    }

    /**
     * Measures two sequential observer-state updates for facing and power. Both
     * results are retained through {@code observer}, including repeated invocations
     * after the requested property values are already set.
     */
    @Benchmark
    public void test2() {
        observer = observer.setPropertyValue(BlockPropertyTypes.MINECRAFT_FACING_DIRECTION, MinecraftFacingDirection.UP);
        observer = observer.setPropertyValue(BlockPropertyTypes.POWERED_BIT, true);
    }
}
