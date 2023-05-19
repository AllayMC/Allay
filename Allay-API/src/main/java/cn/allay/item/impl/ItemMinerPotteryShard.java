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
public interface ItemMinerPotteryShard extends ItemStack {
    ItemType<ItemMinerPotteryShard> TYPE = ItemTypeBuilder
            .builder(ItemMinerPotteryShard.class)
            .vanillaItem(VanillaItemId.MINER_POTTERY_SHARD, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
