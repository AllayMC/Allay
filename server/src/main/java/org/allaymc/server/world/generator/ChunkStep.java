package org.allaymc.server.world.generator;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.allaymc.api.world.chunk.ChunkState;

import java.util.EnumMap;
import java.util.Map;

/**
 * ChunkStep represents how a chunk moved from one state to the next state. It contains
 * the dependencies and the task to complete the upgrade.
 *
 * @author daoge_cmd
 */
@Getter
public final class ChunkStep {
    /**
     * The state of the chunk after this step is completed.
     */
    private final ChunkState targetState;
    /**
     * The state of the chunk before this step is started.
     */
    private final ChunkState parentState;
    /**
     * The dependencies for this step. The key is the required minimum state of the chunks around,
     * and the value is the chunk range. For example, entry (NOISE, 1) means that the lowest state
     * of the eight adjacent chunks surrounding the current chunk must be NOISE.
     */
    private final Map<ChunkState, Integer> dependencies;
    /**
     * The range of chunks that the chunk step task may write to. For example, if the write range is 1,
     * the task may write to the current chunk and the eight adjacent chunks surrounding it. When a chunk
     * is being written to, it will be marked as `locked` and no other tasks can write to it until the current
     * task is completed.
     */
    private final int writeRange;
    /**
     * The task to be executed for completing the state updating.
     */
    private final ChunkStepTask task;

    ChunkStep(ChunkState targetState, Map<ChunkState, Integer> dependencies, int writeRange, ChunkStepTask task) {
        this.targetState = targetState;
        this.parentState = targetState.getParent();
        this.dependencies = dependencies;
        this.writeRange = writeRange;
        this.task = task;
    }

    public static ChunkStepBuilder builder() {
        return new ChunkStepBuilder();
    }

    public static class ChunkStepBuilder {
        private ChunkState targetState;
        private Map<ChunkState, Integer> dependencies;
        private int writeRange;
        private ChunkStepTask task;

        ChunkStepBuilder() {
            this.dependencies = new EnumMap<>(ChunkState.class);
        }

        public ChunkStepBuilder targetState(ChunkState targetState) {
            this.targetState = targetState;
            return this;
        }

        public ChunkStepBuilder dependency(ChunkState dependency, int range) {
            // Dependency must be before target state
            Preconditions.checkArgument(dependency.isBefore(targetState));
            this.dependencies.put(dependency, range);
            return this;
        }

        public ChunkStepBuilder writeRange(int writeRange) {
            this.writeRange = writeRange;
            return this;
        }

        public ChunkStepBuilder task(ChunkStepTask task) {
            this.task = task;
            return this;
        }

        public ChunkStep build() {
            return new ChunkStep(this.targetState, this.dependencies, this.writeRange, this.task);
        }
    }
}
