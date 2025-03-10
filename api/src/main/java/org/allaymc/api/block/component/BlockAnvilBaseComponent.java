package org.allaymc.api.block.component;

import org.allaymc.api.block.type.BlockState;

/**
 * @author IWareQ
 */
public interface BlockAnvilBaseComponent extends BlockBaseComponent {
    /**
     * Applies damage to the anvil block state.
     *
     * @param current the current {@link BlockState}
     *
     * @return the updated {@link BlockState} after damage
     */
    BlockState damage(BlockState current);
}
