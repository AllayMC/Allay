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
public interface ItemGoatSpawnEgg extends ItemStack {
    ItemType<ItemGoatSpawnEgg> TYPE = ItemTypeBuilder
            .builder(ItemGoatSpawnEgg.class)
            .vanillaItem(VanillaItemId.GOAT_SPAWN_EGG, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
