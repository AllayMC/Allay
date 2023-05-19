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
public interface ItemFrogSpawn extends ItemStack {
    ItemType<ItemFrogSpawn> TYPE = ItemTypeBuilder
            .builder(ItemFrogSpawn.class)
            .vanillaItem(VanillaItemId.FROG_SPAWN, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
