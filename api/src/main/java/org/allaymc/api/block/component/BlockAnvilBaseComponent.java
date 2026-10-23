package org.allaymc.api.block.component;

import org.allaymc.api.block.type.BlockState;

/**
 * {@code BlockAnvilBaseComponent} defines damage-state transitions for anvil
 * blocks. Callers obtain a replacement {@code BlockState} from {@code damage} and
 * decide when to publish it; the same component also exposes the ordinary anvil
 * block operations inherited from {@code BlockBaseComponent}.
 *
 * @author IWareQ
 */
public interface BlockAnvilBaseComponent extends BlockBaseComponent {
    /**
     * Returns the state produced by the next anvil damage step. The caller
     * supplies the current anvil state and applies the returned replacement; a
     * fully damaged anvil produces an air state rather than a surviving anvil.
     */
    BlockState damage(BlockState current);
}
