package org.allaymc.api.blockentity.interfaces;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.component.BlockEntityFurnaceBaseComponent;

/**
 * {@code BlockEntityFurnace} represents a furnace with an inventory and
 * persistent burning, cooking, and experience state. Its furnace capability
 * exposes recipe selection and speed information, while the container holder
 * exposes the ingredient, fuel, and result slots used by processing.
 *
 * @author daoge_cmd
 */
public interface BlockEntityFurnace extends BlockEntity, BlockEntityFurnaceBaseComponent, BlockEntityContainerHolderComponent {
}
