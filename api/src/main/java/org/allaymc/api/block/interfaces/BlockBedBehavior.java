package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.component.BlockRespawnPointComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityBed;

/**
 * {@code BlockBedBehavior} exposes bed block behavior together with access to its
 * typed block entity. {@code getBlockEntity} resolves the {@code BlockEntityBed}
 * at a world position, while the inherited {@code BlockBehavior} contract supplies
 * the ordinary placement, update, and interaction entry points.
 * {@code onPlayerRespawn} also resolves the player's destination at the bed.
 */
public interface BlockBedBehavior extends BlockBehavior,
        BlockBlockEntityHolderComponent<BlockEntityBed>,
        BlockRespawnPointComponent {
}
