package org.allaymc.api.block.component;

import org.allaymc.api.block.type.BlockState;

/**
 * @author IWareQ
 */
public interface BlockAnvilBaseComponent extends BlockBaseComponent {
    BlockState damage(BlockState current);
}
