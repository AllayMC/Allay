package org.allaymc.server.world.chunk;

import org.allaymc.api.block.type.BlockState;

/**
 * @author daoge_cmd
 */
public interface BlockChangeCallback {
    void onBlockChange(int x, int y, int z, BlockState blockState, int layer);
}
