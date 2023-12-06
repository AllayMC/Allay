package org.allaymc.api.item.interfaces.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedAndesiteStairsStack extends ItemStack {
    ItemType<ItemPolishedAndesiteStairsStack> POLISHED_ANDESITE_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemPolishedAndesiteStairsStack.class)
            .vanillaItem(VanillaItemId.POLISHED_ANDESITE_STAIRS)
            .build();
}
