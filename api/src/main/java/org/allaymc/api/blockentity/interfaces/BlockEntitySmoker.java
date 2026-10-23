package org.allaymc.api.blockentity.interfaces;

/**
 * {@code BlockEntitySmoker} represents a smoker through the shared furnace
 * contract. Its inherited capabilities expose inventory, fuel, cooking
 * progress, and stored experience; the selected furnace component supplies the
 * smoker block types, recipe category, and specialized cooking speed.
 *
 * @author daoge_cmd
 */
public interface BlockEntitySmoker extends BlockEntityFurnace {
}
