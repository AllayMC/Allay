package org.allaymc.server.world.generator;

import com.google.common.base.Preconditions;
import org.allaymc.api.world.chunk.ChunkState;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.UnaryOperator;

/**
 * {@code ChunkPyramid} maps target generation states to the steps used by chunk
 * holders to reach them. The built-in sequence runs noise, population, and
 * post-processing in state order, with population requiring neighboring noise
 * data and reserving the same neighboring region for writes.
 *
 * <p>
 * A step table describes scheduling requirements rather than executing work
 * itself; holders inspect its dependency states and write ranges before
 * dispatching the associated task. The record retains the supplied map, and
 * builder-produced maps are unmodifiable views over builder state rather than
 * independent snapshots. Continuing to use a builder can therefore change an
 * earlier pyramid's table. {@code ChunkState.NEW} has no incoming step, and
 * custom tables can omit other states without construction-time validation.
 *
 * @param steps {@code steps} is the directly exposed target-to-step table.
 *              Builder output prevents mutation through this reference but
 * retains the builder's backing map.
 *
 * @author daoge_cmd
 */
public record ChunkPyramid(Map<ChunkState, ChunkStep> steps) {

    /**
     * {@code PYRAMID} defines the runtime generation schedule: population requires neighboring
     * {@code ChunkState.NOISE} chunks and reserves that same square for writes.
     */
    public static final ChunkPyramid PYRAMID = new ChunkPyramid.Builder()
            .step(ChunkState.NOISE, builder -> builder.task(ChunkStepTask::doNoise))
            .step(ChunkState.POPULATE, builder -> builder
                    .task(ChunkStepTask::doPopulate)
                    .dependency(ChunkState.NOISE, ChunkStepTask.POPULATION_NEIGHBOR_ACCESS_RANGE)
                    .writeRange(ChunkStepTask.POPULATION_NEIGHBOR_ACCESS_RANGE)
            )
            .step(ChunkState.FULL, builder -> builder.task(ChunkStepTask::doPostProcess))
            .build();

    /**
     * Returns the step targeting the requested state other than {@code
     * ChunkState.NEW}, or {@code null} when a custom table omits it. {@code
     * ChunkState.NEW} is rejected with {@link IllegalArgumentException} because
     * no preceding stage transitions into it.
     */
    public ChunkStep getStepTo(ChunkState state) {
        Preconditions.checkArgument(state != ChunkState.NEW);
        return steps.get(state);
    }

    /**
     * {@code Builder} assembles a generation-step table whose built pyramids retain a live
     * view.
     */
    public static class Builder {
        /**
         * {@code steps} shares this builder's mutable target table with every built
         * pyramid. Later registrations remain visible through earlier
         * unmodifiable views.
         */
        private final Map<ChunkState, ChunkStep> steps = new EnumMap<>(ChunkState.class);

        /**
         * Creates a pyramid exposing an unmodifiable view of this builder's
         * existing table. The backing map is not copied, so later step
         * additions or replacements affect previously built pyramids.
         */
        public ChunkPyramid build() {
            return new ChunkPyramid(Collections.unmodifiableMap(this.steps));
        }

        /**
         * Configures and stores a step under the requested target state using
         * the supplied builder transformation. Existing entries for that target
         * are replaced; the transformation must return a valid builder with
         * consistent target and dependency configuration.
         */
        public ChunkPyramid.Builder step(ChunkState targetState, UnaryOperator<ChunkStep.ChunkStepBuilder> stepFactory) {
            var builder = ChunkStep.builder().targetState(targetState);
            this.steps.put(targetState, stepFactory.apply(builder).build());
            return this;
        }
    }
}
