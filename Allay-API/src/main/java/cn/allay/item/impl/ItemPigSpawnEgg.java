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
public interface ItemPigSpawnEgg extends ItemStack {
    ItemType<ItemPigSpawnEgg> TYPE = ItemTypeBuilder
            .builder(ItemPigSpawnEgg.class)
            .vanillaItem(VanillaItemId.PIG_SPAWN_EGG, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
