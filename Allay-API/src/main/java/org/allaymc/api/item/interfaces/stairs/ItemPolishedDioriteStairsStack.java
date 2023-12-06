package org.allaymc.api.item.interfaces.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedDioriteStairsStack extends ItemStack {
    ItemType<ItemPolishedDioriteStairsStack> POLISHED_DIORITE_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemPolishedDioriteStairsStack.class)
            .vanillaItem(VanillaItemId.POLISHED_DIORITE_STAIRS)
            .build();
}
