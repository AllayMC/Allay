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
public interface ItemAnglerPotteryShard extends ItemStack {
    ItemType<ItemAnglerPotteryShard> TYPE = ItemTypeBuilder
            .builder(ItemAnglerPotteryShard.class)
            .vanillaItem(VanillaItemId.ANGLER_POTTERY_SHARD, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
