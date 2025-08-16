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
    private final ChunkState targetState;
    // Can be null
    private final ChunkState parentState;
    private final Map<ChunkState, Integer> dependencies;
    private final int writeRange;
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
