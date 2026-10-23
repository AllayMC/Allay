package org.allaymc.api.blockentity.interfaces;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.component.BlockEntityDispenserBaseComponent;

/**
 * {@code BlockEntityDropper} represents a dropper with persistent state,
 * inventory access, and the shared dispensing entry point. Its dropper
 * component supplies the item-ejection behavior, while the container holder
 * exposes the inventory used by that operation and by transfer callers.
 *
 * @author daoge_cmd
 */
public interface BlockEntityDropper extends BlockEntity, BlockEntityDispenserBaseComponent, BlockEntityContainerHolderComponent {
}
