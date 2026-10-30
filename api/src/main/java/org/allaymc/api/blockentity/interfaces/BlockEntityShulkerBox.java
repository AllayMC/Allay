package org.allaymc.api.blockentity.interfaces;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;

/**
 * {@code BlockEntityShulkerBox} represents a shulker box with persistent
 * block-entity state and an accessible inventory. The container holder supplies
 * the live inventory, while specialized server components handle orientation,
 * viewer animations, and retaining contents when the block is collected.
 *
 * @author IWareQ
 */
public interface BlockEntityShulkerBox extends BlockEntity, BlockEntityContainerHolderComponent {
}
