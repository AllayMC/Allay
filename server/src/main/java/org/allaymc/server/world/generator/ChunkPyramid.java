package org.allaymc.server.world.generator;

import com.google.common.base.Preconditions;
import org.allaymc.api.world.chunk.ChunkState;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.UnaryOperator;

/**
 * ChunkPyramid represents the sequence of steps required to upgrade a chunk to {@link ChunkState#FULL} state.
 *
 * @author daoge_cmd
 */
public record ChunkPyramid(Map<ChunkState, ChunkStep> steps) {

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

    public static class Builder {
        private final Map<ChunkState, ChunkStep> steps = new EnumMap<>(ChunkState.class);

        public ChunkPyramid build() {
            return new ChunkPyramid(Collections.unmodifiableMap(this.steps));
        }

        public ChunkPyramid.Builder step(ChunkState targetState, UnaryOperator<ChunkStep.ChunkStepBuilder> stepFactory) {
            var builder = ChunkStep.builder().targetState(targetState);
            this.steps.put(targetState, stepFactory.apply(builder).build());
            return this;
        }
    }
}
