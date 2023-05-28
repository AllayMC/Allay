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
public interface ItemPiglinBruteSpawnEgg extends ItemStack {
    ItemType<ItemPiglinBruteSpawnEgg> TYPE = ItemTypeBuilder
            .builder(ItemPiglinBruteSpawnEgg.class)
            .vanillaItem(VanillaItemId.PIGLIN_BRUTE_SPAWN_EGG, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
