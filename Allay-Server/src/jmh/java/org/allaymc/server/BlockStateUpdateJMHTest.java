package org.allaymc.server;

import org.allaymc.api.block.property.enums.PillarAxis;
import org.allaymc.api.block.property.enums.WoodType;
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
    private BlockState wood;

    @Setup
    public void init() throws MissingImplementationException {
        Allay.initAllayAPI();
        wood = BlockTypes.WOOD_TYPE.getDefaultState();
    }

    @Benchmark
    public void test1() {
        wood = wood.setProperty(VanillaBlockPropertyTypes.PILLAR_AXIS, PillarAxis.Z);
    }

    @Benchmark
    public void test2() {
        wood = wood.setProperty(VanillaBlockPropertyTypes.PILLAR_AXIS, PillarAxis.Z);
        wood = wood.setProperty(VanillaBlockPropertyTypes.STRIPPED_BIT, true);
        wood = wood.setProperty(VanillaBlockPropertyTypes.WOOD_TYPE, WoodType.DARK_OAK);
    }
}
