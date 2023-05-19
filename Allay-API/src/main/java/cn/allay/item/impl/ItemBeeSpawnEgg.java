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
public interface ItemBeeSpawnEgg extends ItemStack {
    ItemType<ItemBeeSpawnEgg> TYPE = ItemTypeBuilder
            .builder(ItemBeeSpawnEgg.class)
            .vanillaItem(VanillaItemId.BEE_SPAWN_EGG, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
