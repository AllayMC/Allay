package org.allaymc.api.blockentity.interfaces;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntityBedBaseComponent;

/**
 * {@code BlockEntityBed} exposes the persisted color of a bed together with the
 * shared block-entity contract. Color belongs to the bed base component, while
 * inherited methods provide the bed entity's type, dimension, position, custom
 * name, and NBT representation.
 */
public interface BlockEntityBed extends BlockEntity, BlockEntityBedBaseComponent {
}
