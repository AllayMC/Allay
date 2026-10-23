package org.allaymc.api.blockentity.interfaces;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntityBeaconBaseComponent;

/**
 * {@code BlockEntityBeacon} combines a beacon's persistent block-entity
 * identity with pyramid-level and effect-selection access. The beacon component
 * owns its specialized effect state, while the inherited base contract supplies
 * dimension, location, custom naming, and NBT persistence for the same object.
 *
 * @author daoge_cmd
 */
public interface BlockEntityBeacon extends BlockEntity, BlockEntityBeaconBaseComponent {
}
