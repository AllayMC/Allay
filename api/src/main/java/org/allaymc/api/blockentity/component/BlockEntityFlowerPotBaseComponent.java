package org.allaymc.api.blockentity.component;

import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.item.ItemStack;

/**
 * @author Cdm2883
 */
public interface BlockEntityFlowerPotBaseComponent extends BlockEntityBaseComponent {

    /**
     * Get the plant inside the flower pot.
     *
     * @return the plant block state, or {@code null} if the plant is empty.
     */
    BlockState getPlantBlock();

    /**
     * Set the plant inside the flower pot.
     *
     * @param block the plant block state. Can be {@code null} to clear the plant inside the flower pot.
     *
     * @return {@code true} if it set successfully, otherwise {@code false}.
     */
    boolean trySetPlantBlock(BlockState block);

    /**
     * Get the plant as item inside the flower pot.
     *
     * @return the plant item, or {@code null} if the plant is empty.
     */
    default ItemStack getPlantItem() {
        var block = getPlantBlock();
        if (block == null) return null;
        return block.toItemStack();
    }

    /**
     * Set the plant inside the flower pot.
     *
     * @param item the plant item. Can be {@code null} to clear the plant inside the flower pot.
     *
     * @return {@code true} if it set successfully, otherwise {@code false}.
     */
    default boolean setPlantItem(ItemStack item) {
        if (item == null) return trySetPlantBlock(null);
        return trySetPlantBlock(item.toBlockState());
    }

    /**
     * Check if the flower pot has a plant inside.
     *
     * @return {@code true} if the flower pot has a plant inside, otherwise {@code false}.
     */
    default boolean hasPlant() {
        return getPlantBlock() != null;
    }

    /**
     * Clear the flower pot as empty.
     */
    default void clearPlant() {
        trySetPlantBlock(null);
    }
}
