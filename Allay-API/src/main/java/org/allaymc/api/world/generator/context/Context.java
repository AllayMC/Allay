package org.allaymc.api.world.generator.context;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.chunk.UnsafeChunk;

/**
 * Allay Project 2024/6/16
 *
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
