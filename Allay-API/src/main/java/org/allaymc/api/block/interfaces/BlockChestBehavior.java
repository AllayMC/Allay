package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.common.BlockEntityHolderComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityChest;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockChestBehavior extends
        BlockBehavior,
        BlockEntityHolderComponent<BlockEntityChest> {
}
