package org.allaymc.server;

import org.allaymc.api.block.property.enums.TorchFacingDirection;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.MissingImplementationException;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
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
public class ThroughList2ArrayJMHTest {
    private final BlockPropertyType.BlockPropertyValue<?, ?, ?>[] data1 = new BlockPropertyType.BlockPropertyValue[3];
    private final ArrayList<BlockPropertyType.BlockPropertyValue<?, ?, ?>> data2 = new ArrayList<>(3);

    @Setup
    public void setup() throws MissingImplementationException {
        Allay.initAllay();
        data1[0] = BlockPropertyTypes.AGE_16.createValue(5);
        data1[1] = BlockPropertyTypes.ACTIVE.createValue(false);
        data1[2] = BlockPropertyTypes.TORCH_FACING_DIRECTION.createValue(TorchFacingDirection.WEST);

        data2.add(BlockPropertyTypes.AGE_16.createValue(5));
        data2.add(BlockPropertyTypes.ACTIVE.createValue(false));
        data2.add(BlockPropertyTypes.TORCH_FACING_DIRECTION.createValue(TorchFacingDirection.WEST));
    }

    @Benchmark
    public void arrayForEach(Blackhole blackhole) {
        for (var d : data1) {
            blackhole.consume(d);
        }
    }

    @Benchmark
    public void listForEach(Blackhole blackhole) {
        for (var d : data2) {
            blackhole.consume(d);
        }
    }

    @Benchmark
    public void arrayForI(Blackhole blackhole) {
        for (BlockPropertyType.BlockPropertyValue<?, ?, ?> propertyValue : data1) {
            blackhole.consume(propertyValue);
        }
    }

    @Benchmark
    public void listForI(Blackhole blackhole) {
        for (BlockPropertyType.BlockPropertyValue<?, ?, ?> propertyValue : data2) {
            blackhole.consume(propertyValue);
        }
    }
}
