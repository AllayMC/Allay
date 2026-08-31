package org.allaymc.server;

import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.dimension.DimensionTypes;
import org.allaymc.server.world.chunk.AllayChunkSection;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;
import org.allaymc.server.world.chunk.ChunkEncoder;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

import static org.allaymc.api.block.type.BlockTypes.STONE;

/**
 * @author GoobersAlley
 */
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class SectionEncodeJMHTest {
    private static final int SECTION_Y = 4;
    private AllayUnsafeChunk chunk;
    private Chunk safeChunk;
    private AllayChunkSection section;

    @Setup
    public void init() {
        Allay.initI18n();
        Allay.initAllay();
        chunk = AllayUnsafeChunk.builder().voidChunk(0, 0, DimensionTypes.OVERWORLD);
        safeChunk = chunk.toSafeChunk();
        for (int sectionY = 3; sectionY <= 5; sectionY++) {
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    for (int i = 0; i < 4; i++) {
                        safeChunk.setBlockState(x, sectionY * 16 + i, z, STONE.getDefaultState());
                    }
                }
            }
        }
        section = chunk.getSection(SECTION_Y);
    }

    @Benchmark
    public void encodeSectionUncached(Blackhole blackhole) {
        blackhole.consume(ChunkEncoder.encodeSectionBlob(section));
    }

    @Benchmark
    public void encodeSectionCachedHit(Blackhole blackhole) {
        blackhole.consume(ChunkEncoder.encodeSectionBlob(chunk, SECTION_Y));
    }

    @Benchmark
    public void encodeSectionCachedMiss(Blackhole blackhole) {
        safeChunk.setBlockState(0, SECTION_Y * 16, 0, STONE.getDefaultState());
        blackhole.consume(ChunkEncoder.encodeSectionBlob(chunk, SECTION_Y));
    }

    @Benchmark
    public void encodeBiomesCachedHit(Blackhole blackhole) {
        blackhole.consume(ChunkEncoder.encodeBiomesBlob(chunk));
    }

    @Benchmark
    public void encodeSectionFullChunk(Blackhole blackhole) {
        var buf = ChunkEncoder.writeToNetwork(chunk);
        blackhole.consume(buf);
        buf.release();
    }
}
