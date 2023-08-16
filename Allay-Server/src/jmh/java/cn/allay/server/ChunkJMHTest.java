package cn.allay.server;

import cn.allay.api.MissingImplementationException;
import cn.allay.api.data.VanillaBlockTypes;
import cn.allay.api.world.DimensionInfo;
import cn.allay.api.world.chunk.Chunk;
import cn.allay.server.world.chunk.AllayChunk;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 3)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class ChunkJMHTest {
    private Chunk chunk;

    @Setup
    public void init() throws MissingImplementationException {
        Allay.initAllayAPI();
        chunk = new AllayChunk(0, 0, DimensionInfo.OVERWORLD);
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                for (int k = -64; k < 320; k++) {
                    chunk.setBlockState(i, k, j, VanillaBlockTypes.WOOD_TYPE.getDefaultState());
                }
            }
        }
    }

    /*@Benchmark
    public void test1() {
        chunk.setBlockState(0, 0, 0, VanillaBlockTypes.STONE_TYPE.getDefaultState());
    }

    @Benchmark
    public void test2(Blackhole blackhole) {
        blackhole.consume(chunk.getBlockState(0, 0, 0));
    }*/

    @Threads(Threads.MAX)
    @Benchmark
    public void test3() {
        chunk.setBlockState(0, 0, 0, VanillaBlockTypes.STONE_TYPE.getDefaultState());
    }

    @Threads(Threads.MAX)
    @Benchmark
    public void test4(Blackhole blackhole) {
        blackhole.consume(chunk.getBlockState(0, 0, 0));
    }
}
