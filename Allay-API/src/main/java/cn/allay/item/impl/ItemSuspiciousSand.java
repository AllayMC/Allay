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
public interface ItemSuspiciousSand extends ItemStack {
    ItemType<ItemSuspiciousSand> TYPE = ItemTypeBuilder
            .builder(ItemSuspiciousSand.class)
            .vanillaItem(VanillaItemId.SUSPICIOUS_SAND, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
