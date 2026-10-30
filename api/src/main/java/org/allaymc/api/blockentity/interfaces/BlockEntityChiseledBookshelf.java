package org.allaymc.api.blockentity.interfaces;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntityChiseledBookshelfContainerHolderComponent;

/**
 * {@code BlockEntityChiseledBookshelf} exposes a chiseled bookshelf's container
 * and last-interacted slot alongside persistent base state. The specialized
 * holder supplies the interaction value used by consumers such as redstone
 * logic, while the common block-entity contract supplies location and NBT
 * persistence.
 *
 * @author daoge_cmd
 */
public interface BlockEntityChiseledBookshelf extends BlockEntity, BlockEntityChiseledBookshelfContainerHolderComponent {
}
