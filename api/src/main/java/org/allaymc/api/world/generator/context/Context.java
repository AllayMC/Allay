package org.allaymc.api.world.generator.context;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.world.chunk.UnsafeChunk;
import org.allaymc.api.world.dimension.DimensionType;

/**
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public abstract class Context {
    protected UnsafeChunk currentChunk;

    public DimensionType getDimensionType() {
        return currentChunk.getDimensionType();
    }
}
