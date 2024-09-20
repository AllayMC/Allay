package org.allaymc.server;

import org.allaymc.api.block.property.enums.MinecraftFacingDirection;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.MissingImplementationException;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * @author Cool_Loong
 */
@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 3)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class BlockStateUpdateJMHTest {
    private BlockState observer;

    @Setup
    public void init() throws MissingImplementationException {
        Allay.initAllay();
        observer = BlockTypes.OBSERVER.getDefaultState();
    }

    @Benchmark
    public void test1() {
        observer = observer.setProperty(BlockPropertyTypes.MINECRAFT_FACING_DIRECTION, MinecraftFacingDirection.UP);
    }

    @Benchmark
    public void test2() {
        observer = observer.setProperty(BlockPropertyTypes.MINECRAFT_FACING_DIRECTION, MinecraftFacingDirection.UP);
        observer = observer.setProperty(BlockPropertyTypes.POWERED_BIT, true);
    }
}
