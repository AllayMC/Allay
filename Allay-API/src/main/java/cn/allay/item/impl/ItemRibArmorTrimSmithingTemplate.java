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
public interface ItemRibArmorTrimSmithingTemplate extends ItemStack {
    ItemType<ItemRibArmorTrimSmithingTemplate> TYPE = ItemTypeBuilder
            .builder(ItemRibArmorTrimSmithingTemplate.class)
            .vanillaItem(VanillaItemId.RIB_ARMOR_TRIM_SMITHING_TEMPLATE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
