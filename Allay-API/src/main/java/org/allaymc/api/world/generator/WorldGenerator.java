package org.allaymc.api.world.generator;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.generator.function.EntitySpawner;
import org.allaymc.api.world.generator.function.Noiser;
import org.allaymc.api.world.generator.function.Lighter;
import org.allaymc.api.world.generator.function.Populator;

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

    Chunk generateFinishedChunkSynchronously(int x, int z);

    String getName();

    WorldGeneratorType getType();

    String getPreset();

    void setDimension(Dimension dimension);
    
    interface WorldGeneratorBuilder {
        WorldGeneratorBuilder name(String name);

        WorldGeneratorBuilder type(WorldGeneratorType type);

        WorldGeneratorBuilder preset(String preset);

        WorldGeneratorBuilder noisers(Noiser... noisers);

        WorldGeneratorBuilder populators(Populator... populators);

        WorldGeneratorBuilder lighters(Lighter... lighters);

        WorldGeneratorBuilder entitySpawners(EntitySpawner... entitySpawners);

        WorldGenerator build();
    }

    interface WorldGeneratorBuilderFactory {
        WorldGeneratorBuilder create();
    }
}
