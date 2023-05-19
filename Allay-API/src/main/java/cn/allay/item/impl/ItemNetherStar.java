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
public interface ItemNetherStar extends ItemStack {
    ItemType<ItemNetherStar> TYPE = ItemTypeBuilder
            .builder(ItemNetherStar.class)
            .vanillaItem(VanillaItemId.NETHER_STAR, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
