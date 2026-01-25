package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.component.BlockLecternBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityLectern;

public interface BlockLecternBehavior extends
        BlockBehavior,
        BlockBlockEntityHolderComponent<BlockEntityLectern>,
        BlockLecternBaseComponent {
}
