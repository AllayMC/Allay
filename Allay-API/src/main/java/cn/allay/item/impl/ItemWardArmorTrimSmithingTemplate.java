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
public interface ItemWardArmorTrimSmithingTemplate extends ItemStack {
    ItemType<ItemWardArmorTrimSmithingTemplate> TYPE = ItemTypeBuilder
            .builder(ItemWardArmorTrimSmithingTemplate.class)
            .vanillaItem(VanillaItemId.WARD_ARMOR_TRIM_SMITHING_TEMPLATE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
