package org.allaymc.api.world.generator;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.generator.function.EntitySpawner;
import org.allaymc.api.world.generator.function.Lighter;
import org.allaymc.api.world.generator.function.Noiser;
import org.allaymc.api.world.generator.function.Populator;
import org.jetbrains.annotations.ApiStatus;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * WorldGenerator is responsible for generating chunks.
 *
 * @author daoge_cmd
 */
public interface WorldGenerator {

    ApiInstanceHolder<WorldGeneratorBuilderFactory> BUILDER_FACTORY = ApiInstanceHolder.create();

    /**
     * Create a new WorldGeneratorBuilder.
     *
     * @return the builder
     */
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

    /**
     * Get the name of this generator.
     *
     * @return the name
     */
    String getName();

    /**
     * Get the type of this generator.
     *
     * @return the type
     */
    WorldGeneratorType getType();

    /**
     * Get the preset of this generator.
     *
     * @return the preset
     */
    String getPreset();

    /**
     * Get the dimension of this generator.
     *
     * @return the dimension
     */
    Dimension getDimension();

    /**
     * Set the dimension of this generator.
     * <p>
     * This method is called by the implementation and
     * should only be called by the implementation. Calling
     * this method from outside the implementation will result
     * in throwing IllegalStateException
     *
     * @param dimension the dimension
     * @throws IllegalStateException if the method is called twice
     */
    @ApiStatus.OverrideOnly
    void setDimension(Dimension dimension);

    /**
     * WorldGeneratorBuilder is used to build a new WorldGenerator instance.
     */
    interface WorldGeneratorBuilder {
        /**
         * Set the name of the generator.
         *
         * @param name the name
         * @return the builder
         */
        WorldGeneratorBuilder name(String name);

        /**
         * Set the type of the generator.
         *
         * @param type the type
         * @return the builder
         */
        WorldGeneratorBuilder type(WorldGeneratorType type);

        /**
         * Set the preset of the generator.
         *
         * @param preset the preset
         * @return the builder
         */
        WorldGeneratorBuilder preset(String preset);

        /**
         * Set the noisers of the generator.
         *
         * @param noisers the noisers
         * @return the builder
         */
        WorldGeneratorBuilder noisers(Noiser... noisers);

        /**
         * Set the populators of the generator.
         *
         * @param populators the populators
         * @return the builder
         */
        WorldGeneratorBuilder populators(Populator... populators);

        /**
         * Set the lighters of the generator.
         *
         * @param lighters the lighters
         * @return the builder
         */
        WorldGeneratorBuilder lighters(Lighter... lighters);

        /**
         * Set the entity spawners of the generator.
         *
         * @param entitySpawners the entity spawners
         * @return the builder
         */
        WorldGeneratorBuilder entitySpawners(EntitySpawner... entitySpawners);

        /**
         * Set the callback which will be called when the dimension is set.
         *
         * @param onDimensionSet the callback
         * @return the builder
         */
        WorldGeneratorBuilder onDimensionSet(Consumer<Dimension> onDimensionSet);

        /**
         * Build the WorldGenerator instance.
         *
         * @return the WorldGenerator
         */
        WorldGenerator build();
    }

    interface WorldGeneratorBuilderFactory {
        WorldGeneratorBuilder create();
    }
}
