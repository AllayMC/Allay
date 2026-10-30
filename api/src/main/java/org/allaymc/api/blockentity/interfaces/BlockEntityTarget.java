package org.allaymc.api.blockentity.interfaces;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntityTargetBaseComponent;

/**
 * {@code BlockEntityTarget} exposes target block active power together with the
 * block-entity contract. The target component supplies the remembered redstone
 * power value while the shared block-entity contract supplies position,
 * dimension, type, custom naming, and persistent NBT storage for the target.
 *
 * @author daoge_cmd
 */
public interface BlockEntityTarget extends BlockEntity, BlockEntityTargetBaseComponent {
}
