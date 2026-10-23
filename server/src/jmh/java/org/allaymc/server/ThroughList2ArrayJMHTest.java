package org.allaymc.server;

import org.allaymc.api.block.property.enums.TorchFacingDirection;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * {@code ThroughList2ArrayJMHTest} measures traversal of three prepared block-property
 * values held in an array or an array list. Every benchmark uses an enhanced-for loop and
 * passes each element to a blackhole so the traversal result is observed.
 * @author Cool_Loong
 */
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class ThroughList2ArrayJMHTest {
    /**
     * {@code data1} and {@code data2} hold the same three property-value choices in array
     * and list form. Setup creates each container's values separately before measured
     * traversal.
     */
    private final BlockPropertyType.BlockPropertyValue<?, ?, ?>[] data1 = new BlockPropertyType.BlockPropertyValue[3]; // three-value array traversal fixture
    /**
     * Three-value list used by the traversal workloads.
     */
    private final ArrayList<BlockPropertyType.BlockPropertyValue<?, ?, ?>> data2 = new ArrayList<>(3);

    /**
     * Initializes the server property registries and prepares matching array and list
     * fixtures. Construction stays outside measured invocations, leaving each benchmark to
     * traverse and consume its three prepared values.
     */
    @Setup
    public void setup() {
        Allay.initI18n();
        Allay.initAllay();
        data1[0] = BlockPropertyTypes.AGE_16.createValue(5);
        data1[1] = BlockPropertyTypes.ACTIVE.createValue(false);
        data1[2] = BlockPropertyTypes.TORCH_FACING_DIRECTION.createValue(TorchFacingDirection.WEST);

        data2.add(BlockPropertyTypes.AGE_16.createValue(5));
        data2.add(BlockPropertyTypes.ACTIVE.createValue(false));
        data2.add(BlockPropertyTypes.TORCH_FACING_DIRECTION.createValue(TorchFacingDirection.WEST));
    }

    /**
     * Measures enhanced-for traversal of the prepared three-element array. Each element is
     * passed to {@code blackhole} within the measured invocation so the benchmark observes
     * the values without constructing new fixtures.
     */
    @Benchmark
    public void arrayForEach(Blackhole blackhole) {
        for (var d : data1) {
            blackhole.consume(d);
        }
    }

    /**
     * Measures enhanced-for traversal of the prepared three-element array list. Each
     * element is passed to {@code blackhole} within the measured invocation so the
     * benchmark observes the values without constructing new fixtures.
     */
    @Benchmark
    public void listForEach(Blackhole blackhole) {
        for (var d : data2) {
            blackhole.consume(d);
        }
    }

    /**
     * Measures enhanced-for traversal of the prepared three-element array. Each element is
     * passed to {@code blackhole} within the measured invocation so the benchmark observes
     * the values without constructing new fixtures.
     */
    @Benchmark
    public void arrayForI(Blackhole blackhole) {
        for (BlockPropertyType.BlockPropertyValue<?, ?, ?> propertyValue : data1) {
            blackhole.consume(propertyValue);
        }
    }

    /**
     * Measures enhanced-for traversal of the prepared three-element array list. Each
     * element is passed to {@code blackhole} within the measured invocation so the
     * benchmark observes the values without constructing new fixtures.
     */
    @Benchmark
    public void listForI(Blackhole blackhole) {
        for (BlockPropertyType.BlockPropertyValue<?, ?, ?> propertyValue : data2) {
            blackhole.consume(propertyValue);
        }
    }
}
