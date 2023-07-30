package cn.allay.server;

import cn.allay.api.MissingImplementationException;
import cn.allay.api.block.property.enums.TorchFacingDirection;
import cn.allay.api.block.property.type.BlockPropertyType;
import cn.allay.api.data.VanillaBlockPropertyTypes;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

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
        Allay.initAllayAPI();
        data1[0] = VanillaBlockPropertyTypes.AGE_16.createValue(5);
        data1[1] = VanillaBlockPropertyTypes.ACTIVE.createValue(false);
        data1[2] = VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION.createValue(TorchFacingDirection.WEST);

        data2.add(VanillaBlockPropertyTypes.AGE_16.createValue(5));
        data2.add(VanillaBlockPropertyTypes.ACTIVE.createValue(false));
        data2.add(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION.createValue(TorchFacingDirection.WEST));
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
        for (int i = 0; i < data1.length; i++) {
            blackhole.consume(data1[i]);
        }
    }

    @Benchmark
    public void listForI(Blackhole blackhole) {
        for (int i = 0; i < data2.size(); i++) {
            blackhole.consume(data2.get(i));
        }
    }
}
