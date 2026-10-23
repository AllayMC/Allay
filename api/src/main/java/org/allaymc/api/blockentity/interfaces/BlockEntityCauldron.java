package org.allaymc.api.blockentity.interfaces;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntityCauldronBaseComponent;

/**
 * {@code BlockEntityCauldron} exposes cauldron potion and custom-color data
 * alongside its persistent block-entity identity. The specialized component
 * distinguishes potion effects from bottle variants, while inherited base
 * methods provide position, dimension, custom naming, and NBT storage for the
 * same cauldron.
 *
 * @author daoge_cmd
 */
public interface BlockEntityCauldron extends BlockEntity, BlockEntityCauldronBaseComponent {
}
