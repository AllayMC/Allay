package cn.allay.api.item.impl;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;
import cn.allay.api.item.type.ItemTypeRegistry;

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
