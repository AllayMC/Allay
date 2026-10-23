package org.allaymc.server;

import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.dimension.DimensionTypes;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

import static org.allaymc.api.block.type.BlockTypes.OAK_WOOD;
import static org.allaymc.api.block.type.BlockTypes.STONE;

/**
 * {@code ChunkJMHTest} measures
 * safe chunk block reads and writes with single-threaded and contended
 * JMH workloads.
 *
 * @author Cool_Loong
 */
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class ChunkJMHTest {
    /**
     * {@code chunk} is shared by benchmark workers after setup. Every measured
     * operation accesses its safe wrapper so workers use section locking.
     */
    private Chunk chunk;

    /**
     * Initializes registries and fills one empty Overworld chunk with oak wood
     * before measurement. The resulting safe wrapper is shared by benchmark
     * threads, and setup cost is excluded from the measured operations.
     */
    @Setup
    public void init() {
        Allay.initI18n();
        Allay.initAllay();
        chunk = AllayUnsafeChunk.builder().voidChunk(0, 0, DimensionTypes.OVERWORLD).toSafeChunk();
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                for (int k = -64; k < 320; k++) {
                    chunk.setBlockState(i, k, j, OAK_WOOD.getDefaultState());
                }
            }
        }
    }

    /**
     * Measures repeated safe block writes at one fixed position using the
     * default single worker. After the first replacement, subsequent
     * invocations write the same stone state.
     */
    @Benchmark
    public void test1() {
        chunk.setBlockState(0, 0, 0, STONE.getDefaultState());
    }

    /**
     * Measures a safe block read at one fixed position using the default single
     * worker. {@code blackhole} consumes the result so the benchmark retains
     * observable work.
     */
    @Benchmark
    public void test2(Blackhole blackhole) {
        blackhole.consume(chunk.getBlockState(0, 0, 0));
    }

    /**
     * Measures safe block writes with the maximum JMH worker count contending
     * on one section. Every invocation targets the same position and assigns
     * the stone state.
     */
    @Threads(Threads.MAX)
    @Benchmark
    public void test3() {
        chunk.setBlockState(0, 0, 0, STONE.getDefaultState());
    }

    /**
     * Measures safe block reads with the maximum JMH worker count sharing one
     * section. {@code blackhole} consumes each retrieved state while readers
     * contend for shared locking machinery.
     */
    @Threads(Threads.MAX)
    @Benchmark
    public void test4(Blackhole blackhole) {
        blackhole.consume(chunk.getBlockState(0, 0, 0));
    }
}
