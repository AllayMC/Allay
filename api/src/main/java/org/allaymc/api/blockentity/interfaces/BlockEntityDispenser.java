package org.allaymc.api.blockentity.interfaces;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.component.BlockEntityDispenserBaseComponent;

/**
 * {@code BlockEntityDispenser} represents a dispenser with persistent state, a
 * live inventory, and a dispensing operation. The dispensing capability selects
 * behavior for an inventory item, while the container holder exposes the same
 * inventory to viewers and item-transfer callers.
 *
 * @author daoge_cmd
 */
public interface BlockEntityDispenser extends BlockEntity, BlockEntityDispenserBaseComponent, BlockEntityContainerHolderComponent {
}
