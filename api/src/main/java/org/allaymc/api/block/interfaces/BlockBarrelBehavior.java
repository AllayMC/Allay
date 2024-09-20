package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockEntityHolderComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityBarrel;

public interface BlockBarrelBehavior extends
        BlockBehavior,
        BlockEntityHolderComponent<BlockEntityBarrel> {
}
