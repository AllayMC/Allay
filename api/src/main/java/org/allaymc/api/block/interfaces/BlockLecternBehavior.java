package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.component.BlockLecternBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityLectern;

/**
 * {@code BlockLecternBehavior} exposes lectern block behavior together with access
 * to its typed block entity. {@code getBlockEntity} resolves the
 * {@code BlockEntityLectern} at a world position, while the inherited
 * {@code BlockBehavior} contract supplies the ordinary placement, update, and
 * interaction entry points. The lectern component additionally accepts page
 * changes from the reading interface.
 */
public interface BlockLecternBehavior extends
        BlockBehavior,
        BlockBlockEntityHolderComponent<BlockEntityLectern>,
        BlockLecternBaseComponent {
}
