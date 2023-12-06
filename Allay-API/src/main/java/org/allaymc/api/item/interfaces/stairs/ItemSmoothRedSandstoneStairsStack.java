package org.allaymc.api.item.interfaces.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSmoothRedSandstoneStairsStack extends ItemStack {
    ItemType<ItemSmoothRedSandstoneStairsStack> SMOOTH_RED_SANDSTONE_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemSmoothRedSandstoneStairsStack.class)
            .vanillaItem(VanillaItemId.SMOOTH_RED_SANDSTONE_STAIRS)
            .build();
}
