package org.allaymc.api.blockentity.component;

import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.item.ItemStack;

/**
 * {@code BlockEntityFlowerPotBaseComponent} exposes a flower pot's optional
 * plant block and item conversion operations. The server accepts plant states
 * carrying the pottable-plant tag, represents an empty pot with {@code null},
 * and persists the plant's block-state NBT.
 *
 * @author Cdm2883
 */
public interface BlockEntityFlowerPotBaseComponent extends BlockEntityBaseComponent {

    /**
     * Returns the plant block state, or {@code null} for an empty pot. The
     * server retains the supplied block state directly; item conversion uses
     * that state's ordinary block-to-item operation.
     */
    BlockState getPlantBlock();

    /**
     * Stores a pottable plant state, or clears the pot for {@code null}. The
     * server rejects other non-{@code null} states without mutation and updates
     * the pot's block property and viewers after accepting a change.
     */
    boolean trySetPlantBlock(BlockState block);

    /**
     * Converts the current plant block state into its item representation. An
     * empty pot returns {@code null}; a present plant delegates conversion to
     * the stored state's {@code toItemStack} operation.
     */
    default ItemStack getPlantItem() {
        var block = getPlantBlock();
        if (block == null) {
            return null;
        }

        return block.toItemStack();
    }

    /**
     * Converts an item to a block state and attempts to store it as the plant.
     * {@code null} clears the pot. A non-block item whose conversion returns
     * {@code null} also follows that clearing path rather than being rejected.
     */
    default boolean setPlantItem(ItemStack item) {
        if (item == null) {
            return trySetPlantBlock(null);
        }

        return trySetPlantBlock(item.toBlockState());
    }

    /**
     * Returns whether the pot currently retains a non-{@code null} plant state.
     */
    default boolean hasPlant() {
        return getPlantBlock() != null;
    }

    /**
     * Clears the plant through {@code trySetPlantBlock} with {@code null}. The
     * method discards the operation's boolean result and retains the ordinary
     * block-property and viewer-update side effects of that operation.
     */
    default void clearPlant() {
        trySetPlantBlock(null);
    }
}
