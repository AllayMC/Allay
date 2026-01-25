package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.component.BlockRespawnPointComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityBed;

public interface BlockBedBehavior extends BlockBehavior,
        BlockBlockEntityHolderComponent<BlockEntityBed>,
        BlockRespawnPointComponent {
}
