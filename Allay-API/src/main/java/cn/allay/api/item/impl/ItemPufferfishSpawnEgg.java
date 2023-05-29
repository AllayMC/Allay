package cn.allay.api.item.impl;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;
import cn.allay.api.item.type.ItemTypeRegistry;

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
