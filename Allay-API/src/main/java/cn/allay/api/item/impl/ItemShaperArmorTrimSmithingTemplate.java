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
public interface ItemShaperArmorTrimSmithingTemplate extends ItemStack {
    ItemType<ItemShaperArmorTrimSmithingTemplate> TYPE = ItemTypeBuilder
            .builder(ItemShaperArmorTrimSmithingTemplate.class)
            .vanillaItem(VanillaItemId.SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
