package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityEnchantTable;

/**
 * {@code BlockEnchantingTableBehavior} exposes enchanting table block behavior
 * together with access to its typed block entity. {@code getBlockEntity} resolves
 * the {@code BlockEntityEnchantTable} at a world position, while inherited block
 * behavior supplies the ordinary placement, update, and interaction entry points.
 */
public interface BlockEnchantingTableBehavior extends
        BlockBehavior,
        BlockBlockEntityHolderComponent<BlockEntityEnchantTable> {
}
