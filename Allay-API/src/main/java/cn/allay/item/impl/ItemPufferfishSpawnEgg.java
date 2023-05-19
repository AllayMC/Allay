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
public interface ItemPufferfishSpawnEgg extends ItemStack {
    ItemType<ItemPufferfishSpawnEgg> TYPE = ItemTypeBuilder
            .builder(ItemPufferfishSpawnEgg.class)
            .vanillaItem(VanillaItemId.PUFFERFISH_SPAWN_EGG, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
