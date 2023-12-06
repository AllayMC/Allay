package org.allaymc.api.item.interfaces.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSandstoneStairsStack extends ItemStack {
    ItemType<ItemSandstoneStairsStack> SANDSTONE_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemSandstoneStairsStack.class)
            .vanillaItem(VanillaItemId.SANDSTONE_STAIRS)
            .build();
}
