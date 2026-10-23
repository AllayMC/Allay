package org.allaymc.api.world.generator.context;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.world.chunk.UnsafeChunk;
import org.allaymc.api.world.dimension.DimensionType;

/**
 * {@code Context} supplies the current unsafe chunk to one generation-stage callback. The
 * context retains that shared chunk rather than copying it, and the generation
 * scheduler establishes the permitted mutation region and execution lifetime
 * before invoking functions that use it.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public abstract class Context {
    /**
     * {@code currentChunk} retains the generation target. Direct access is valid
     * only within the scheduler's assigned stage and reserved mutation region.
     */
    protected UnsafeChunk currentChunk;

    /**
     * Returns the dimension type associated with this context's current chunk.
     */
    public DimensionType getDimensionType() {
        return currentChunk.getDimensionType();
    }
}
