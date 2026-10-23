package org.allaymc.server.world.generator;

import org.allaymc.api.world.chunk.ChunkSource;
import org.allaymc.api.world.chunk.UnsafeChunk;
import org.allaymc.api.world.generator.WorldGenerator;
import org.allaymc.api.world.generator.context.NoiseContext;
import org.allaymc.api.world.generator.context.PopulateContext;
import org.allaymc.api.world.generator.context.PostProcessContext;

/**
 * {@code ChunkStepTask} executes one generation stage against a reserved current chunk and any
 * permitted neighbors. Static stage adapters construct the matching context and
 * invoke every configured function in list order; returned booleans are
 * ignored, and the same current chunk is returned afterward.
 *
 * @author daoge_cmd
 */
@FunctionalInterface
public interface ChunkStepTask {

    /**
     * {@code POPULATION_NEIGHBOR_ACCESS_RANGE} sets population access and write
     * reservation to the current chunk plus its eight surrounding neighbors.
     */
    int POPULATION_NEIGHBOR_ACCESS_RANGE = 1;

    /**
     * Invokes all configured noise functions in order using a context
     * containing only the current chunk. Boolean results are ignored,
     * exceptions stop later functions, and the same unsafe chunk is returned.
     */
    static UnsafeChunk doNoise(WorldGenerator worldGenerator, UnsafeChunk currentChunk, ChunkSource chunkSource) {
        var context = new NoiseContext(currentChunk);
        worldGenerator.getNoisers().forEach(noiser -> noiser.apply(context));
        return currentChunk;
    }

    /**
     * Invokes all population functions using a source restricted to the
     * surrounding one-chunk square. Boolean results are ignored, exceptions
     * stop later functions, and the caller must already have reserved the
     * region for writes.
     */
    static UnsafeChunk doPopulate(WorldGenerator worldGenerator, UnsafeChunk currentChunk, ChunkSource chunkSource) {
        var context = new PopulateContext(currentChunk, new LimitedChunkSource(chunkSource, currentChunk.getX(), currentChunk.getZ(), POPULATION_NEIGHBOR_ACCESS_RANGE));
        worldGenerator.getPopulators().forEach(populator -> populator.apply(context));
        return currentChunk;
    }

    /**
     * Invokes all configured post-processors in order using a context
     * containing only the current chunk. Boolean results are ignored,
     * exceptions stop later functions, and the same unsafe chunk is returned.
     */
    static UnsafeChunk doPostProcess(WorldGenerator worldGenerator, UnsafeChunk currentChunk, ChunkSource chunkSource) {
        var context = new PostProcessContext(currentChunk);
        worldGenerator.getPostProcessors().forEach(postProcessor -> postProcessor.apply(context));
        return currentChunk;
    }

    /**
     * Performs the configured transition using the supplied current chunk and
     * accessible neighboring source. The caller establishes stage prerequisites
     * and write reservations; task failures may leave partial mutations for the
     * holder to handle.
     */
    UnsafeChunk doWork(WorldGenerator worldGenerator, UnsafeChunk currentChunk, ChunkSource chunkSource);
}
