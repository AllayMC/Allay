package org.allaymc.api.item.interfaces.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBirchStairsStack extends ItemStack {
    ItemType<ItemBirchStairsStack> BIRCH_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemBirchStairsStack.class)
            .vanillaItem(VanillaItemId.BIRCH_STAIRS)
            .build();
}
