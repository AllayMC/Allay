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
public interface ItemCactus extends ItemStack {
    ItemType<ItemCactus> TYPE = ItemTypeBuilder
            .builder(ItemCactus.class)
            .vanillaItem(VanillaItemId.CACTUS, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
