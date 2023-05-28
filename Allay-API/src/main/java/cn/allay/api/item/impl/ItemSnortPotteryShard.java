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
public interface ItemSnortPotteryShard extends ItemStack {
    ItemType<ItemSnortPotteryShard> TYPE = ItemTypeBuilder
            .builder(ItemSnortPotteryShard.class)
            .vanillaItem(VanillaItemId.SNORT_POTTERY_SHARD, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
