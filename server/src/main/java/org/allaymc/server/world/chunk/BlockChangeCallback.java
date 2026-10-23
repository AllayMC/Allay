package org.allaymc.server.world.chunk;

import org.allaymc.api.block.type.BlockState;

/**
 * {@code BlockChangeCallback} receives
 * a block replacement from an unsafe chunk after its palette changes.
 * Coordinates use chunk-local X and Z with absolute block Y; the chunk loader
 * installs this callback to forward primary-layer changes into lighting
 * updates.
 *
 * @author daoge_cmd
 */
public interface BlockChangeCallback {
    /**
     * Handles a replaced block state at chunk-local X and Z and absolute Y. The
     * callback runs during the mutation and must respect the caller's existing
     * chunk locks and execution context.
     */
    void onBlockChange(int x, int y, int z, BlockState blockState, int layer);
}
