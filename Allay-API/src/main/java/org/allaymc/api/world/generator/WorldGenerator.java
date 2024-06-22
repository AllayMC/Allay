package org.allaymc.api.world.generator;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.generator.function.EntitySpawner;
import org.allaymc.api.world.generator.function.Lighter;
import org.allaymc.api.world.generator.function.Noiser;
import org.allaymc.api.world.generator.function.Populator;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * Allay Project 2024/6/16
 *
 * @author daoge_cmd
 */
public interface WorldGenerator {

    ApiInstanceHolder<WorldGeneratorBuilderFactory> BUILDER_FACTORY = ApiInstanceHolder.create();

    static WorldGeneratorBuilder builder() {
        return BUILDER_FACTORY.get().create();
    }

    /**
     * Generate a chunk.
     * <p>
     * This method may be called by any thread, so implementations should ensure thread safety.
     *
     * @param x chunk x coordinate
     * @param z chunk z coordinate
     *
     * @return generated chunk
     */
    CompletableFuture<Chunk> generateChunk(int x, int z);

    String getName();

    WorldGeneratorType getType();

    String getPreset();

    Dimension getDimension();

    void setDimension(Dimension dimension);

    interface WorldGeneratorBuilder {
        WorldGeneratorBuilder name(String name);

        WorldGeneratorBuilder type(WorldGeneratorType type);

        WorldGeneratorBuilder preset(String preset);

        WorldGeneratorBuilder noisers(Noiser... noisers);

        WorldGeneratorBuilder populators(Populator... populators);

        WorldGeneratorBuilder lighters(Lighter... lighters);

        WorldGeneratorBuilder entitySpawners(EntitySpawner... entitySpawners);

        WorldGeneratorBuilder onDimensionSet(Consumer<Dimension> onDimensionSet);

        WorldGenerator build();
    }

    interface WorldGeneratorBuilderFactory {
        WorldGeneratorBuilder create();
    }
}
