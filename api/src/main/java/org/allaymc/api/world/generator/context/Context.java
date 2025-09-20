package org.allaymc.api.world.generator.context;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.world.chunk.UnsafeChunk;
import org.allaymc.api.world.data.DimensionInfo;

/**
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public abstract class Context {
    protected UnsafeChunk currentChunk;

    public DimensionInfo getDimensionInfo() {
        return currentChunk.getDimensionInfo();
    }
}
