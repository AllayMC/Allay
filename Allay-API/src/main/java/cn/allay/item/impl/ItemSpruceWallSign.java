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
public interface ItemSpruceWallSign extends ItemStack {
    ItemType<ItemSpruceWallSign> TYPE = ItemTypeBuilder
            .builder(ItemSpruceWallSign.class)
            .vanillaItem(VanillaItemId.SPRUCE_WALL_SIGN, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
