package org.allaymc.api.world.generator;

import lombok.AccessLevel;
import lombok.Getter;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.generator.function.GenerateFunction;
import org.allaymc.api.world.generator.function.Noiser;
import org.allaymc.api.world.generator.function.Populator;
import org.allaymc.api.world.generator.function.PostProcessor;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * WorldGenerator is responsible for generating chunks in the dimension. It is composed of multiple {@link GenerateFunction}
 * which will be called in different generation stages based on their types.
 *
 * @author daoge_cmd
 */
@Getter
public class WorldGenerator {

    /**
     * The name of the generator.
     */
    private final String name;
    /**
     * A preset string that will be passed to the generator instance. The format of the preset string depends
     * on the generator implementation, for example, the generator implementation can require text preset to a
     * JSON format.
     */
    private final String preset;

    private final List<Noiser> noisers;
    private final List<Populator> populators;
    private final List<PostProcessor> postProcessors;

    @Getter(AccessLevel.NONE)
    private final Consumer<Dimension> dimensionConsumer;

    /**
     * Dimension that this generator is associated with. Will be set later.
     */
    private Dimension dimension;

    private WorldGenerator(
            String name,
            String preset,
            List<Noiser> noisers,
            List<Populator> populators,
            List<PostProcessor> postProcessors,
            Consumer<Dimension> dimensionConsumer
    ) {
        this.name = name;
        this.preset = preset;
        this.noisers = Collections.unmodifiableList(noisers);
        this.populators = Collections.unmodifiableList(populators);
        this.postProcessors = Collections.unmodifiableList(postProcessors);
        this.noisers.forEach(noiser -> noiser.init(preset));
        this.populators.forEach(populator -> populator.init(preset));
        this.postProcessors.forEach(postProcessor -> postProcessor.init(preset));
        this.dimensionConsumer = dimensionConsumer;
    }

    /**
     * Create a new WorldGeneratorBuilder instance.
     *
     * @return a new WorldGeneratorBuilder
     */
    public static WorldGeneratorBuilder builder() {
        return new WorldGeneratorBuilder();
    }

    /**
     * Set the dimension of this generator.
     * <p>
     * This method is called by the implementation and should only be called by the implementation. Calling
     * this method from outside the implementation will result in throwing {@link IllegalStateException}.
     *
     * @param dimension the dimension
     * @throws IllegalStateException if the method is called twice
     */
    public void setDimension(Dimension dimension) {
        if (this.dimension != null) {
            throw new IllegalStateException("Dimension has already been set for this generator: " + this.name);
        }

        this.dimension = dimension;
        this.dimensionConsumer.accept(dimension);
    }

    public static final class WorldGeneratorBuilder {

        private String name;
        private String preset = "";
        private List<Noiser> noisers = List.of();
        private List<Populator> populators = List.of();
        private List<PostProcessor> postProcessors = List.of();
        private Consumer<Dimension> dimensionConsumer = $ -> {
        };

        private WorldGeneratorBuilder() {
        }

        /**
         * Set the name of the generator.
         *
         * @param name the name
         * @return the builder
         */
        public WorldGeneratorBuilder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Set the preset of the generator.
         *
         * @param preset the preset
         * @return the builder
         */
        public WorldGeneratorBuilder preset(String preset) {
            this.preset = preset;
            return this;
        }

        /**
         * Set the noisers of the generator.
         *
         * @param noisers the noisers
         * @return the builder
         */
        public WorldGeneratorBuilder noisers(Noiser... noisers) {
            this.noisers = List.of(noisers);
            return this;
        }

        /**
         * Set the populators of the generator.
         *
         * @param populators the populators
         * @return the builder
         */
        public WorldGeneratorBuilder populators(Populator... populators) {
            this.populators = List.of(populators);
            return this;
        }

        /**
         * Set the post processors of the generator.
         *
         * @param postProcessors the post processors
         * @return the builder
         */
        public WorldGeneratorBuilder postProcessors(PostProcessor... postProcessors) {
            this.postProcessors = List.of(postProcessors);
            return this;
        }

        /**
         * Set a consumer that will be called when the dimension is set.
         * This is used to set the dimension of the generator.
         *
         * @param dimensionConsumer the consumer to call with the dimension
         * @return the builder
         */
        public WorldGeneratorBuilder onDimensionSet(Consumer<Dimension> dimensionConsumer) {
            this.dimensionConsumer = dimensionConsumer;
            return this;
        }

        /**
         * Build the WorldGenerator instance.
         *
         * @return the WorldGenerator
         */
        public WorldGenerator build() {
            if (name == null || name.isBlank()) {
                throw new IllegalStateException("Name cannot be null or blank");
            }
            return new WorldGenerator(name, preset, noisers, populators, postProcessors, dimensionConsumer);
        }
    }
}
