package org.allaymc.api.world;

import org.allaymc.api.block.type.BlockState;

/**
 * A type specified four parameters function interface to avoid packing/unpacking for better performance.
 *
 * @author daoge_cmd
 */
@FunctionalInterface
public interface PosAndBlockStateConsumer {
    void apply(int x, int y, int z, BlockState blockState);
}
