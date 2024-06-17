package org.allaymc.api.world.generator;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.generator.function.EntitySpawner;
import org.allaymc.api.world.generator.function.Noiser;
import org.allaymc.api.world.generator.function.Lighter;
import org.allaymc.api.world.generator.function.Populator;

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

    Chunk generateChunk(int x, int z);

    String getName();

    WorldGeneratorType getType();

    String getPreset();

    void setDimension(Dimension dimension);

    Dimension getDimension();
    
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
