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
public interface ItemSnortPotteryShard extends ItemStack {
    ItemType<ItemSnortPotteryShard> TYPE = ItemTypeBuilder
            .builder(ItemSnortPotteryShard.class)
            .vanillaItem(VanillaItemId.SNORT_POTTERY_SHARD, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
