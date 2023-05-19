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
public interface ItemZoglinSpawnEgg extends ItemStack {
    ItemType<ItemZoglinSpawnEgg> TYPE = ItemTypeBuilder
            .builder(ItemZoglinSpawnEgg.class)
            .vanillaItem(VanillaItemId.ZOGLIN_SPAWN_EGG, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
