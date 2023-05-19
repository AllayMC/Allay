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
public interface ItemJungleWallSign extends ItemStack {
    ItemType<ItemJungleWallSign> TYPE = ItemTypeBuilder
            .builder(ItemJungleWallSign.class)
            .vanillaItem(VanillaItemId.JUNGLE_WALL_SIGN, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
