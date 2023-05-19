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
public interface ItemWildArmorTrimSmithingTemplate extends ItemStack {
    ItemType<ItemWildArmorTrimSmithingTemplate> TYPE = ItemTypeBuilder
            .builder(ItemWildArmorTrimSmithingTemplate.class)
            .vanillaItem(VanillaItemId.WILD_ARMOR_TRIM_SMITHING_TEMPLATE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
