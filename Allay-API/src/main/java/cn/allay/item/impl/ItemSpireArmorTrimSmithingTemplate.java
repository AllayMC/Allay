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
public interface ItemSpireArmorTrimSmithingTemplate extends ItemStack {
    ItemType<ItemSpireArmorTrimSmithingTemplate> TYPE = ItemTypeBuilder
            .builder(ItemSpireArmorTrimSmithingTemplate.class)
            .vanillaItem(VanillaItemId.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
