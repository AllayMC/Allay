package org.allaymc.api.container.interfaces;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.container.Container;
import org.allaymc.api.item.ItemStack;

/**
 * {@code SidedContainer} describes which container slots automation may insert into or
 * extract from for a block face. Implementations return candidate indices rather than
 * moving items; an empty result denies access, and consumers must handle {@code null} as
 * the unrestricted sentinel.
 *
 * @author ClexaGod
 */
public interface SidedContainer extends Container {
    /**
     * Returns insertion candidates for the supplied face and item stack. An empty array
     * denies insertion, while {@code null} lets transfer code consider every slot;
     * callers still perform capacity and merging checks.
     */
    int[] getAllowedInsertSlots(BlockFace side, ItemStack stack);

    /**
     * Returns extraction candidates for the supplied block face. An empty array denies
     * extraction, while {@code null} represents unrestricted access; consumers must
     * handle that sentinel before iterating the returned slot indices.
     */
    int[] getAllowedExtractSlots(BlockFace side);
}
