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
public interface ItemTraderLlamaSpawnEgg extends ItemStack {
    ItemType<ItemTraderLlamaSpawnEgg> TYPE = ItemTypeBuilder
            .builder(ItemTraderLlamaSpawnEgg.class)
            .vanillaItem(VanillaItemId.TRADER_LLAMA_SPAWN_EGG, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
