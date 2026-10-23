package org.allaymc.api.blockentity.interfaces;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntityEnderChestBaseComponent;

/**
 * {@code BlockEntityEnderChest} represents an ender chest's world-side state
 * and viewer count. Player-owned ender-chest inventories remain separate
 * containers; this block entity supplies identity, location, persistence, and
 * the count used to signal opening and closing at the block.
 *
 * @author IWareQ
 */
public interface BlockEntityEnderChest extends BlockEntity, BlockEntityEnderChestBaseComponent {
}
