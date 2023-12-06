package org.allaymc.api.item.interfaces.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooMosaicStairsStack extends ItemStack {
    ItemType<ItemBambooMosaicStairsStack> BAMBOO_MOSAIC_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemBambooMosaicStairsStack.class)
            .vanillaItem(VanillaItemId.BAMBOO_MOSAIC_STAIRS)
            .build();
}
