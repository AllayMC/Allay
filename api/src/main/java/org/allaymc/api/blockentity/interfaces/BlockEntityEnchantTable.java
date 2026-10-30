package org.allaymc.api.blockentity.interfaces;

import org.allaymc.api.blockentity.BlockEntity;

/**
 * {@code BlockEntityEnchantTable} exposes enchantment table identity together
 * with the block-entity contract. The common block-entity contract supplies
 * type, position, dimension, naming, and NBT persistence; this marker adds no
 * separate operations for the table book animation.
 *
 * @author daoge_cmd
 */
public interface BlockEntityEnchantTable extends BlockEntity {
}
