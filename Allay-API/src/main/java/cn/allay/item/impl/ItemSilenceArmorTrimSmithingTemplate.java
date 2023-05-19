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
public interface ItemSilenceArmorTrimSmithingTemplate extends ItemStack {
    ItemType<ItemSilenceArmorTrimSmithingTemplate> TYPE = ItemTypeBuilder
            .builder(ItemSilenceArmorTrimSmithingTemplate.class)
            .vanillaItem(VanillaItemId.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
