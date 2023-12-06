package org.allaymc.api.item.interfaces.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedBlackstoneBrickStairsStack extends ItemStack {
    ItemType<ItemPolishedBlackstoneBrickStairsStack> POLISHED_BLACKSTONE_BRICK_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemPolishedBlackstoneBrickStairsStack.class)
            .vanillaItem(VanillaItemId.POLISHED_BLACKSTONE_BRICK_STAIRS)
            .build();
}
