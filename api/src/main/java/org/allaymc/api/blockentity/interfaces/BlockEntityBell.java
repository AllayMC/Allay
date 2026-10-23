package org.allaymc.api.blockentity.interfaces;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntityBellBaseComponent;

/**
 * {@code BlockEntityBell} combines a bell's persistent block-entity state with
 * ringing controls and animation state. The bell capability exposes direction
 * and elapsed ringing ticks, while the shared base contract supplies the same
 * object's location, type identity, and NBT persistence.
 *
 * @author daoge_cmd
 */
public interface BlockEntityBell extends BlockEntity, BlockEntityBellBaseComponent {
}
