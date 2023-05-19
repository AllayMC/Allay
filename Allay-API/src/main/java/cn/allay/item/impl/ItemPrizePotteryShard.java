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
public interface ItemPrizePotteryShard extends ItemStack {
    ItemType<ItemPrizePotteryShard> TYPE = ItemTypeBuilder
            .builder(ItemPrizePotteryShard.class)
            .vanillaItem(VanillaItemId.PRIZE_POTTERY_SHARD, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
