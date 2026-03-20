package org.allaymc.server.container.impl;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.SidedContainer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.data.ItemTags;
import org.allaymc.api.item.interfaces.ItemAirStack;

import java.util.Arrays;

/**
 * @author daoge_cmd
 */
public class ChiseledBookshelfContainerImpl extends BaseContainer implements SidedContainer {

    public ChiseledBookshelfContainerImpl() {
        super(ContainerTypes.CHISELED_BOOKSHELF);
    }

    @Override
    public int[] getAllowedInsertSlots(BlockFace side, ItemStack stack) {
        if (!stack.getItemType().hasItemTag(ItemTags.CHISELED_BOOKSHELF_BOOK)) {
            return new int[0];
        }

        return collectSlots(true);
    }

    @Override
    public int[] getAllowedExtractSlots(BlockFace side) {
        return collectSlots(false);
    }

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
