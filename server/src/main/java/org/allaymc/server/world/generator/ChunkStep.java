package org.allaymc.server.world.generator;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.allaymc.api.world.chunk.ChunkState;

import java.util.EnumMap;
import java.util.Map;

/**
 * {@code ChunkStep} describes one generation transition and the neighboring stages and write
 * region required by its task. Chunk holders use this metadata to delay work
 * until dependencies are ready and to mark the selected region busy while the
 * task runs.
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
     * {@code dependencies} maps minimum stages to square radii and is exposed
     * by its getter. Later builder changes affect holders inspecting these
     * requirements.
     */
    private final Map<ChunkState, Integer> dependencies;
    /**
     * {@code writeRange} is the inclusive square radius reserved by holder busy
     * flags for task writes. These logical flags alone do not synchronize
     * shared access.
     */
    private final int writeRange;
    /**
     * The task to be executed for completing the state updating.
     */
    private final ChunkStepTask task;

    /**
     * Creates transition metadata retaining the supplied dependency map and
     * task reference. The parent stage is derived from the target, and no copy
     * or validation ensures that task writes stay within the configured range.
     */
    ChunkStep(ChunkState targetState, Map<ChunkState, Integer> dependencies, int writeRange, ChunkStepTask task) {
        this.targetState = targetState;
        this.parentState = targetState.getParent();
        this.dependencies = dependencies;
        this.writeRange = writeRange;
        this.task = task;
    }

    /**
     * Returns a new builder for one chunk generation transition.
     */
    public static ChunkStepBuilder builder() {
        return new ChunkStepBuilder();
    }

    /**
     * {@code ChunkStepBuilder} collects transition metadata and shares its
     * dependency map with built steps.
     */
    public static class ChunkStepBuilder {
        /**
         * {@code dependencies} is the mutable table retained by built steps.
         * Changing a later builder entry also changes earlier step metadata.
         */
        private final Map<ChunkState, Integer> dependencies;
        /**
         * {@code targetState} identifies the requested transition endpoint.
         * Dependency checks and building require this initially {@code null}
         * value to be set first.
         */
        private ChunkState targetState;
        /**
         * {@code writeRange} is the inclusive write-region radius in chunks. Zero
         * selects only the target chunk; negative values are not rejected.
         */
        private int writeRange;
        /**
         * {@code task} is retained in built steps. Callers must set it before a
         * holder attempts to execute the transition.
         */
        private ChunkStepTask task;

        /**
         * Creates a transition builder with an empty dependency table.
         */
        ChunkStepBuilder() {
            this.dependencies = new EnumMap<>(ChunkState.class);
        }

        /**
         * Selects the target generation stage and returns this builder.
         * Callers set the target before adding dependencies or building,
         * because those operations compare stages or derive the parent from
         * it.
         */
        public ChunkStepBuilder targetState(ChunkState targetState) {
            this.targetState = targetState;
            return this;
        }

        /**
         * Adds or replaces a square-radius requirement for an earlier
         * generation stage. The target must already be set, and the dependency
         * must precede it; the range itself is not validated here.
         */
        public ChunkStepBuilder dependency(ChunkState dependency, int range) {
            // Requiring only earlier states avoids directly requesting this same
            // target stage from neighboring chunks as a prerequisite for its completion.
            Preconditions.checkArgument(dependency.isBefore(targetState));
            this.dependencies.put(dependency, range);
            return this;
        }

        /**
         * Sets the inclusive square radius that holders reserve for task
         * writes. The default zero selects only the current chunk; this setter
         * does not validate negative or oversized ranges.
         */
        public ChunkStepBuilder writeRange(int writeRange) {
            this.writeRange = writeRange;
            return this;
        }

        /**
         * Selects the task retained by the built transition and returns this
         * builder. Callers must supply a task before a holder executes the
         * step; building does not reject a missing task.
         */
        public ChunkStepBuilder task(ChunkStepTask task) {
            this.task = task;
            return this;
        }

        /**
         * Creates a step sharing this builder's dependency map and configured
         * task. The target must be non-{@code null} for parent lookup, but missing
         * tasks and inconsistent ranges are not otherwise rejected.
         */
        public ChunkStep build() {
            return new ChunkStep(this.targetState, this.dependencies, this.writeRange, this.task);
        }
    }
}
