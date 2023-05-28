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
public interface ItemWardArmorTrimSmithingTemplate extends ItemStack {
    ItemType<ItemWardArmorTrimSmithingTemplate> TYPE = ItemTypeBuilder
            .builder(ItemWardArmorTrimSmithingTemplate.class)
            .vanillaItem(VanillaItemId.WARD_ARMOR_TRIM_SMITHING_TEMPLATE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
