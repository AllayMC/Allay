package org.allaymc.api.blockentity.interfaces;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.component.BlockEntityHopperBaseComponent;

/**
 * {@code BlockEntityHopper} represents a hopper with persistent block-entity
 * state, inventory access, and a transfer cooldown. The holder exposes the live
 * inventory used for pushing and pulling items, while the hopper capability
 * exposes the delay controlling subsequent transfer attempts.
 *
 * @author ClexaGod
 */
public interface BlockEntityHopper extends BlockEntity, BlockEntityHopperBaseComponent, BlockEntityContainerHolderComponent {
}
