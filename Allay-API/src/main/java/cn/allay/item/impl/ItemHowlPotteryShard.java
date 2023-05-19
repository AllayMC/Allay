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
public interface ItemHowlPotteryShard extends ItemStack {
    ItemType<ItemHowlPotteryShard> TYPE = ItemTypeBuilder
            .builder(ItemHowlPotteryShard.class)
            .vanillaItem(VanillaItemId.HOWL_POTTERY_SHARD, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
