package cn.allay.api.item.impl;

import cn.allay.api.item.ItemStack;
import cn.allay.api.item.data.VanillaItemId;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;
import cn.allay.api.item.type.ItemTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPigSpawnEgg extends ItemStack {
    ItemType<ItemPigSpawnEgg> TYPE = ItemTypeBuilder
            .builder(ItemPigSpawnEgg.class)
            .vanillaItem(VanillaItemId.PIG_SPAWN_EGG, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
