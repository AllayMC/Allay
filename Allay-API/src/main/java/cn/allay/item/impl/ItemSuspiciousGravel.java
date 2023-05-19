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
public interface ItemSuspiciousGravel extends ItemStack {
    ItemType<ItemSuspiciousGravel> TYPE = ItemTypeBuilder
            .builder(ItemSuspiciousGravel.class)
            .vanillaItem(VanillaItemId.SUSPICIOUS_GRAVEL, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
