package org.allaymc.api.block.component;

import org.allaymc.api.block.type.BlockState;

/**
 * Shared component for anvils that can transition between damage states.
 *
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
