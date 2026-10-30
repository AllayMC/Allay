package org.allaymc.api.blockentity.interfaces;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;

/**
 * {@code BlockEntityShelf} exposes shelf inventory access together with the
 * block-entity contract. The container-holder capability supplies the shelf
 * container and item-drop policy while the common block-entity contract
 * supplies position, type, dimension, custom naming, and NBT persistence for
 * the shelf.
 *
 * @author daoge_cmd
 */
public interface BlockEntityShelf extends BlockEntity, BlockEntityContainerHolderComponent {
}
