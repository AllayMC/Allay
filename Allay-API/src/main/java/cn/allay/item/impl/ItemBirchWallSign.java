package cn.allay.item.impl;

import cn.allay.item.ItemStack;
import cn.allay.item.data.VanillaItemId;
import cn.allay.item.type.ItemType;
import cn.allay.item.type.ItemTypeBuilder;
import cn.allay.item.type.ItemTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBirchWallSign extends ItemStack {
    ItemType<ItemBirchWallSign> TYPE = ItemTypeBuilder
            .builder(ItemBirchWallSign.class)
            .vanillaItem(VanillaItemId.BIRCH_WALL_SIGN, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
