package org.allaymc.api.blockentity.interfaces;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntityChestContainerHolderComponent;
import org.allaymc.api.blockentity.component.BlockEntityPairableComponent;

/**
 * {@code BlockEntityChest} represents a chest with its own inventory and an
 * optional relationship to another chest. The holder exposes a combined
 * container for paired use, while the pairing capability identifies the other
 * chest and the lead member of that relationship.
 *
 * @author daoge_cmd
 */
public interface BlockEntityChest extends BlockEntity, BlockEntityChestContainerHolderComponent, BlockEntityPairableComponent {
}
