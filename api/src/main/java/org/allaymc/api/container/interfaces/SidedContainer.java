package org.allaymc.api.container.interfaces;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.container.Container;
import org.allaymc.api.item.ItemStack;

/**
 * A container that restricts insertion/extraction by side.
 *
 * @author ClexaGod
 */
public interface SidedContainer extends Container {
    /**
     * Gets the allowed insert slots for the given side and item.
     *
     * @param side  the side the item is inserted from
     * @param stack the item being inserted
     * @return allowed slots, empty for none, or {@code null} for no restriction
     */
    int[] getAllowedInsertSlots(BlockFace side, ItemStack stack);

    /**
     * Gets the allowed extract slots for the given side.
     *
     * @param side the side the item is extracted from
     * @return allowed slots, empty for none, or {@code null} for no restriction
     */
    int[] getAllowedExtractSlots(BlockFace side);
}
