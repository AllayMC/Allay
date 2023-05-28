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
public interface ItemFrogSpawnEgg extends ItemStack {
    ItemType<ItemFrogSpawnEgg> TYPE = ItemTypeBuilder
            .builder(ItemFrogSpawnEgg.class)
            .vanillaItem(VanillaItemId.FROG_SPAWN_EGG, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
