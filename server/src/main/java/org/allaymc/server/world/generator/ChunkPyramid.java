package org.allaymc.server.world.generator;

import com.google.common.base.Preconditions;
import org.allaymc.api.world.chunk.ChunkState;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.UnaryOperator;

/**
 * @author daoge_cmd
 */
public record ChunkPyramid(Map<ChunkState, ChunkStep> steps, Map<ChunkState, Integer> maxDependentRanges,
                           int maxDependentRange) {

    public static final ChunkPyramid PYRAMID = new ChunkPyramid.Builder()
            .step(ChunkState.NOISE, builder -> builder.task(ChunkStepTask::doNoise))
            .step(ChunkState.POPULATE, builder -> builder
                    .task(ChunkStepTask::doPopulate)
                    .dependency(ChunkState.NOISE, ChunkStepTask.POPULATION_NEIGHBOR_ACCESS_RANGE)
                    .writeRange(ChunkStepTask.POPULATION_NEIGHBOR_ACCESS_RANGE)
            )
            .step(ChunkState.FULL, builder -> builder.task(ChunkStepTask::doPostProcess))
            .build();

    public ChunkStep getStepTo(ChunkState state) {
        Preconditions.checkArgument(state != ChunkState.NEW);
        return steps.get(state);
    }

    public int getMaxDependentRange(ChunkState state) {
        Preconditions.checkArgument(state != ChunkState.NEW);
        return maxDependentRanges.getOrDefault(state, 0);
    }

    public static class Builder {
        private final Map<ChunkState, ChunkStep> steps = new EnumMap<>(ChunkState.class);

        public ChunkPyramid build() {
            var maxDependentRanges = new EnumMap<ChunkState, Integer>(ChunkState.class);
            for (var step : steps.values()) {
                for (var entry : step.getDependencies().entrySet()) {
                    maxDependentRanges.merge(entry.getKey(), entry.getValue(), Math::max);
                }
            }

            for (ChunkState state : ChunkState.values()) {
                for (ChunkState higher : ChunkState.values()) {
                    if (higher.isOrAfter(state)) {
                        maxDependentRanges.merge(higher, maxDependentRanges.getOrDefault(state, 0), Math::max);
                    }
                }
            }

            return new ChunkPyramid(
                    Collections.unmodifiableMap(this.steps),
                    Collections.unmodifiableMap(maxDependentRanges),
                    maxDependentRanges.values().stream().max(Integer::compareTo).orElse(0)
            );
        }

        public ChunkPyramid.Builder step(ChunkState targetState, UnaryOperator<ChunkStep.ChunkStepBuilder> stepFactory) {
            var builder = ChunkStep.builder().targetState(targetState);
            this.steps.put(targetState, stepFactory.apply(builder).build());
            return this;
        }
    }
}
