package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityCauldron;

public interface BlockCauldronBehavior extends
        BlockBehavior,
        BlockBlockEntityHolderComponent<BlockEntityCauldron> {
}
