package org.allaymc.server.container.impl;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.SidedContainer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.data.ItemTags;
import org.allaymc.api.item.interfaces.ItemAirStack;

import java.util.Arrays;

/**
 * {@code ChiseledBookshelfContainerImpl} stores the six bookshelf slots and supplies
 * automation candidates based on occupancy. Insertions require the bookshelf book tag
 * and target empty slots; extraction targets occupied slots, with the same eligibility
 * applied regardless of the supplied block face.
 *
 * @author daoge_cmd
 */
public class ChiseledBookshelfContainerImpl extends BaseContainer implements SidedContainer {

    /**
     * Creates empty six-slot storage using the chiseled bookshelf container identity.
     */
    public ChiseledBookshelfContainerImpl() {
        super(ContainerTypes.CHISELED_BOOKSHELF);
    }

    /**
     * Returns empty slots when the stack has the bookshelf book tag. Other items receive
     * no candidates, and {@code side} does not change the selected slots; {@code stack}
     * must be non-{@code null}.
     */
    @Override
    public int[] getAllowedInsertSlots(BlockFace side, ItemStack stack) {
        if (!stack.getItemType().hasItemTag(ItemTags.CHISELED_BOOKSHELF_BOOK)) {
            return new int[0];
        }

        return collectSlots(true);
    }

    /**
     * Returns the currently occupied bookshelf slots in ascending index order. The
     * selection ignores {@code side}, so every face exposes the same occupied storage
     * positions to the extraction consumer.
     */
    @Override
    public int[] getAllowedExtractSlots(BlockFace side) {
        return collectSlots(false);
    }

    /**
     * Collects slot indices whose air-sentinel state matches {@code empty}. The result
     * is a new array containing only matching indices in ascending order, with no unused
     * capacity entries returned to callers.
     */
    private int[] collectSlots(boolean empty) {
        var size = this.content.length;
        var slots = new int[size];
        var length = 0;
        for (int slot = 0; slot < size; slot++) {
            if ((content[slot] == ItemAirStack.AIR_STACK) == empty) {
                slots[length++] = slot;
            }
        }

        return Arrays.copyOf(slots, length);
    }
}
