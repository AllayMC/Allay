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
public interface ItemArmsUpPotteryShard extends ItemStack {
    ItemType<ItemArmsUpPotteryShard> TYPE = ItemTypeBuilder
            .builder(ItemArmsUpPotteryShard.class)
            .vanillaItem(VanillaItemId.ARMS_UP_POTTERY_SHARD, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
