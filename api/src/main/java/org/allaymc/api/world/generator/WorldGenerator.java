package org.allaymc.api.world.generator;

import lombok.AccessLevel;
import lombok.Getter;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.generator.function.GenerateFunction;
import org.allaymc.api.world.generator.function.Noiser;
import org.allaymc.api.world.generator.function.Populator;
import org.allaymc.api.world.generator.function.PostProcessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * {@code WorldGenerator} organizes the noise, population, and post-processing functions used to
 * generate a dimension's chunks. Construction initializes each retained
 * function with the preset, while generator initialization events can append
 * more functions before the dimension is assigned.
 *
 * <p>
 * Assigning a non-{@code null} dimension locks further additions and then invokes the
 * configured dimension callback. The reference is stored before that callback
 * runs, so callback failure does not reopen configuration. Function lists are
 * exposed as unmodifiable lists, but their elements remain shared and may serve
 * concurrent chunk-generation tasks. The builder can reuse those elements
 * across generators, causing their initialization methods to run again; callers
 * must manage that sharing and complete configuration before generation begins.
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
     * {@code preset} holds the configuration text passed unchanged to each
     * generation function. Each implementation defines its accepted format and
     * validation behavior.
     */
    private final String preset;

    // The noisers, populators, and postProcessors lists preserve stage order
    // and expose unmodifiable membership. Additions replace one list before
    // dimension assignment; function instances remain shared across generation
    // tasks.
    /**
     * Ordered terrain-generation functions.
     */
    private List<Noiser> noisers;
    /**
     * Ordered neighboring-feature functions.
     */
    private List<Populator> populators;
    /**
     * Ordered final-stage generation functions.
     */
    private List<PostProcessor> postProcessors;

    /**
     * {@code dimensionConsumer} runs after {@code dimension} is assigned.
     * Callback failure leaves that reference stored and does not reopen
     * configuration.
     */
    @Getter(AccessLevel.NONE)
    private final Consumer<Dimension> dimensionConsumer;

    /**
     * {@code dimension} starts as {@code null}. A non-{@code null} value prevents
     * later function additions and another dimension assignment.
     */
    private Dimension dimension;

    /**
     * Creates a generator retaining function instances and initializing them in
     * noise, population, then post-processing order. The supplied lists are
     * wrapped without copying, so callers must not mutate those backing lists
     * after construction. Every function receives the same preset before the
     * dimension callback is retained. An initialization failure aborts
     * construction without undoing earlier function initialization, and shared
     * function instances can therefore retain changes from an unsuccessful
     * build.
     */
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
     * Returns a new mutable builder for a dimension's generation pipeline.
     */
    public static WorldGeneratorBuilder builder() {
        return new WorldGeneratorBuilder();
    }

    /**
     * Appends a noise function after initializing it with this generator's
     * preset. Callers must serialize configuration and invoke this operation
     * before assigning a non-{@code null} dimension, normally during
     * {@code WorldGeneratorInitEvent}.
     *
     * @param noiser the noiser to add
     * @throws IllegalStateException if the dimension has already been set
     */
    public void addNoiser(Noiser noiser) {
        checkNotLocked();
        // Initialize the candidate before publishing its list membership; a
        // failed initialization leaves this generator's list unchanged but does
        // not undo mutations inside the shared function.
        noiser.init(preset);
        var list = new ArrayList<>(noisers);
        list.add(noiser);
        noisers = Collections.unmodifiableList(list);
    }

    /**
     * Appends a population function after initializing it with this generator's
     * preset. Callers must serialize configuration and invoke this operation
     * before assigning a non-{@code null} dimension, normally during
     * {@code WorldGeneratorInitEvent}.
     *
     * @param populator the populator to add
     * @throws IllegalStateException if the dimension has already been set
     */
    public void addPopulator(Populator populator) {
        checkNotLocked();
        // Initialize the candidate before publishing its list membership; a
        // failed initialization leaves this generator's list unchanged but does
        // not undo mutations inside the shared function.
        populator.init(preset);
        var list = new ArrayList<>(populators);
        list.add(populator);
        populators = Collections.unmodifiableList(list);
    }

    /**
     * Appends a post-processing function after initializing it with this
     * generator's preset. Callers must serialize configuration and invoke this
     * operation before assigning a non-{@code null} dimension, normally during
     * {@code WorldGeneratorInitEvent}.
     *
     * @param postProcessor the post-processor to add
     * @throws IllegalStateException if the dimension has already been set
     */
    public void addPostProcessor(PostProcessor postProcessor) {
        checkNotLocked();
        // Initialize the candidate before publishing its list membership; a
        // failed initialization leaves this generator's list unchanged but does
        // not undo mutations inside the shared function.
        postProcessor.init(preset);
        var list = new ArrayList<>(postProcessors);
        list.add(postProcessor);
        postProcessors = Collections.unmodifiableList(list);
    }

    /**
     * Rejects configuration changes after a non-{@code null} dimension has been
     * assigned. This is a lifecycle guard rather than synchronization, so
     * callers must serialize initialization with dimension assignment.
     */
    private void checkNotLocked() {
        if (dimension != null) {
            throw new IllegalStateException("Cannot modify generator after dimension has been set: " + name);
        }
    }

    /**
     * Assigns the owning dimension and invokes the configured callback after
     * storing the reference. This method is reserved for runtime dimension
     * initialization; the implementation checks only whether a non-{@code null}
     * dimension was previously stored. A callback exception leaves that
     * reference assigned and further configuration locked. Passing {@code null} does
     * not establish the lock and still invokes the callback, so runtime callers
     * must supply their actual non-{@code null} dimension.
     */
    public void setDimension(Dimension dimension) {
        if (this.dimension != null) {
            throw new IllegalStateException("Dimension has already been set for this generator: " + this.name);
        }

        // Publish the dimension before invoking its callback so callbacks can
        // query ownership; a callback failure leaves the generator assigned
        // instead of reopening its configuration phase.
        this.dimension = dimension;
        this.dimensionConsumer.accept(dimension);
    }

    /**
     * {@code WorldGeneratorBuilder} collects generator configuration while
     * retaining the supplied generation-function instances. Building
     * initializes those shared instances and can be repeated with the same
     * builder.
     */
    public static final class WorldGeneratorBuilder {

        /**
         * {@code name} holds the required generator name. {@code build} rejects
         * its initial {@code null} value and any blank replacement text.
         */
        private String name;
        /**
         * {@code preset} defaults to {@code ""} and is passed to each function.
         * Each implementation interprets the text using its own configuration
         * format.
         */
        private String preset = "";
        /**
         * {@code noisers} retains noise functions in an immutable membership
         * list. Each build initializes the same supplied instances again.
         */
        private List<Noiser> noisers = List.of();
        /**
         * {@code populators} retains population functions in an immutable
         * membership list. Each build initializes the same supplied instances
         * again.
         */
        private List<Populator> populators = List.of();
        /**
         * {@code postProcessors} retains final-stage functions in an immutable
         * membership list. Each build initializes the same supplied instances
         * again.
         */
        private List<PostProcessor> postProcessors = List.of();
        /**
         * {@code dimensionConsumer} runs after ownership assignment and defaults
         * to no action. Generators built from this configuration share the
         * callback instance.
         */
        private Consumer<Dimension> dimensionConsumer = $ -> {
        };

        /**
         * Creates an empty generator configuration with no generation functions
         * selected.
         */
        private WorldGeneratorBuilder() {
        }

        /**
         * Selects the name that identifies a built generator and returns this
         * builder. The setter stores {@code null} or blank text unchanged;
         * {@code build} performs required-name validation when construction is
         * requested.
         */
        public WorldGeneratorBuilder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the preset text passed unchanged to every configured generation
         * function. The builder defaults to an empty string; each function
         * decides its own preset format and validation behavior.
         */
        public WorldGeneratorBuilder preset(String preset) {
            this.preset = preset;
            return this;
        }

        /**
         * Replaces the ordered noise functions and returns this builder. The
         * varargs elements are retained in an unmodifiable list, {@code null} elements
         * are rejected, and building initializes the shared function instances.
         */
        public WorldGeneratorBuilder noisers(Noiser... noisers) {
            this.noisers = List.of(noisers);
            return this;
        }

        /**
         * Replaces the ordered population functions and returns this builder.
         * The varargs elements are retained in an unmodifiable list, {@code null}
         * elements are rejected, and building initializes the shared function
         * instances.
         */
        public WorldGeneratorBuilder populators(Populator... populators) {
            this.populators = List.of(populators);
            return this;
        }

        /**
         * Replaces the ordered post-processing functions and returns this
         * builder. The varargs elements are retained in an unmodifiable list,
         * {@code null} elements are rejected, and building initializes the shared
         * function instances.
         */
        public WorldGeneratorBuilder postProcessors(PostProcessor... postProcessors) {
            this.postProcessors = List.of(postProcessors);
            return this;
        }

        /**
         * Replaces the callback invoked after a generator receives its
         * dimension and returns this builder. The callback must be non-{@code null} for
         * successful assignment and can observe the stored dimension reference.
         */
        public WorldGeneratorBuilder onDimensionSet(Consumer<Dimension> dimensionConsumer) {
            this.dimensionConsumer = dimensionConsumer;
            return this;
        }

        /**
         * Creates and initializes a generator after rejecting a {@code null} or blank
         * name. Function instances are retained and initialized again on every
         * build, so repeated builds can affect previously built generators
         * sharing those functions.
         */
        public WorldGenerator build() {
            if (name == null || name.isBlank()) {
                throw new IllegalStateException("Name cannot be null or blank");
            }
            return new WorldGenerator(name, preset, noisers, populators, postProcessors, dimensionConsumer);
        }
    }
}
