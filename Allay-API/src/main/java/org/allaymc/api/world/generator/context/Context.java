package org.allaymc.api.world.generator.context;

import lombok.Getter;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.chunk.UnsafeChunk;

/**
 * Allay Project 2024/6/16
 *
 * @author daoge_cmd
 */
public abstract class Context {
    @Getter
    protected UnsafeChunk currentChunk;

    public Context(UnsafeChunk currentChunk) {
        this.currentChunk = currentChunk;
    }

    public DimensionInfo getDimensionInfo() {
        return currentChunk.getDimensionInfo();
    }
}
