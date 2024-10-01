package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockEntityHolderComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityEnchantTable;

public interface BlockEnchantingTableBehavior extends
        BlockBehavior,
        BlockEntityHolderComponent<BlockEntityEnchantTable> {
}
