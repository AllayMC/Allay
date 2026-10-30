package org.allaymc.api.blockentity.interfaces;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntityHeadBaseComponent;

/**
 * {@code BlockEntityHead} exposes head orientation and animation state together
 * with the block-entity contract. The head component supplies compass direction
 * and animation values while the common base supplies type, position,
 * dimension, custom naming, and NBT persistence for this block entity.
 *
 * @author daoge_cmd
 */
public interface BlockEntityHead extends BlockEntity, BlockEntityHeadBaseComponent {
}
