package org.allaymc.server.world.generator;

import org.allaymc.api.world.chunk.ChunkSource;
import org.allaymc.api.world.chunk.UnsafeChunk;
import org.allaymc.api.world.generator.WorldGenerator;
import org.allaymc.api.world.generator.context.NoiseContext;
import org.allaymc.api.world.generator.context.PopulateContext;
import org.allaymc.api.world.generator.context.PostProcessContext;

/**
 * ChunkStepTask represents the task that needs to be executed for updating chunk to a higher state.
 *
 * @author daoge_cmd
 */
@FunctionalInterface
public interface ChunkStepTask {

    int POPULATION_NEIGHBOR_ACCESS_RANGE = 1;

    static UnsafeChunk doNoise(WorldGenerator worldGenerator, UnsafeChunk currentChunk, ChunkSource chunkSource) {
        var context = new NoiseContext(currentChunk);
        worldGenerator.getNoisers().forEach(noiser -> noiser.apply(context));
        return currentChunk;
    }

    static UnsafeChunk doPopulate(WorldGenerator worldGenerator, UnsafeChunk currentChunk, ChunkSource chunkSource) {
        var context = new PopulateContext(currentChunk, new LimitedChunkSource(chunkSource, currentChunk.getX(), currentChunk.getZ(), POPULATION_NEIGHBOR_ACCESS_RANGE));
        worldGenerator.getPopulators().forEach(populator -> populator.apply(context));
        return currentChunk;
    }

    static UnsafeChunk doPostProcess(WorldGenerator worldGenerator, UnsafeChunk currentChunk, ChunkSource chunkSource) {
        var context = new PostProcessContext(currentChunk);
        worldGenerator.getPostProcessors().forEach(postProcessor -> postProcessor.apply(context));
        return currentChunk;
    }

    UnsafeChunk doWork(WorldGenerator worldGenerator, UnsafeChunk currentChunk, ChunkSource chunkSource);
}
