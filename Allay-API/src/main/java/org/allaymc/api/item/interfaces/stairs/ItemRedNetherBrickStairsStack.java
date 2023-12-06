package org.allaymc.api.item.interfaces.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedNetherBrickStairsStack extends ItemStack {
    ItemType<ItemRedNetherBrickStairsStack> RED_NETHER_BRICK_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemRedNetherBrickStairsStack.class)
            .vanillaItem(VanillaItemId.RED_NETHER_BRICK_STAIRS)
            .build();
}
