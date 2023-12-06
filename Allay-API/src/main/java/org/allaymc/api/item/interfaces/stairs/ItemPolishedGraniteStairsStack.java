package org.allaymc.api.item.interfaces.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedGraniteStairsStack extends ItemStack {
    ItemType<ItemPolishedGraniteStairsStack> POLISHED_GRANITE_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemPolishedGraniteStairsStack.class)
            .vanillaItem(VanillaItemId.POLISHED_GRANITE_STAIRS)
            .build();
}
