package org.allaymc.api.blockentity.interfaces;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;

/**
 * {@code BlockEntityBarrel} represents a barrel with persistent block-entity
 * state and an accessible inventory. The container-holder capability exposes
 * that barrel's live container, while the base contract supplies its type,
 * dimension, position, custom name, and NBT serialization.
 *
 * @author daoge_cmd
 */
public interface BlockEntityBarrel extends BlockEntity, BlockEntityContainerHolderComponent {
}
