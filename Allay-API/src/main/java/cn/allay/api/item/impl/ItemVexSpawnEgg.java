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
public interface ItemVexSpawnEgg extends ItemStack {
    ItemType<ItemVexSpawnEgg> TYPE = ItemTypeBuilder
            .builder(ItemVexSpawnEgg.class)
            .vanillaItem(VanillaItemId.VEX_SPAWN_EGG, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
