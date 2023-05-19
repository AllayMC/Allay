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
public interface ItemNpcSpawnEgg extends ItemStack {
    ItemType<ItemNpcSpawnEgg> TYPE = ItemTypeBuilder
            .builder(ItemNpcSpawnEgg.class)
            .vanillaItem(VanillaItemId.NPC_SPAWN_EGG, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
