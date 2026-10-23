package org.allaymc.api.blockentity.interfaces;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntityBrewingStandBaseComponent;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;

/**
 * {@code BlockEntityBrewingStand} represents a brewing stand with an inventory,
 * brewing progress, and fuel state. Its container and brewing capabilities
 * belong to the same block entity, whose base contract supplies identity,
 * position, naming, and NBT serialization.
 *
 * @author IWareQ
 */
public interface BlockEntityBrewingStand extends BlockEntity, BlockEntityContainerHolderComponent, BlockEntityBrewingStandBaseComponent {
}
