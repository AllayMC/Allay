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
public interface ItemArmsUpPotteryShard extends ItemStack {
    ItemType<ItemArmsUpPotteryShard> TYPE = ItemTypeBuilder
            .builder(ItemArmsUpPotteryShard.class)
            .vanillaItem(VanillaItemId.ARMS_UP_POTTERY_SHARD, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
