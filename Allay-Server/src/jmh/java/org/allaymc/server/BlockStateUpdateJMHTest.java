package org.allaymc.server;

import org.allaymc.api.block.property.enums.MinecraftFacingDirection;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockPropertyTypes;
import org.allaymc.api.utils.exception.MissingImplementationException;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Allay Project 2023/6/1
 *
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
        Allay.initAllayAPI();
        observer = BlockTypes.OBSERVER_TYPE.getDefaultState();
    }

    @Benchmark
    public void test1() {
        observer = observer.setProperty(VanillaBlockPropertyTypes.MINECRAFT_FACING_DIRECTION, MinecraftFacingDirection.UP);
    }

    @Benchmark
    public void test2() {
        observer = observer.setProperty(VanillaBlockPropertyTypes.MINECRAFT_FACING_DIRECTION, MinecraftFacingDirection.UP);
        observer = observer.setProperty(VanillaBlockPropertyTypes.POWERED_BIT, true);
    }
}
