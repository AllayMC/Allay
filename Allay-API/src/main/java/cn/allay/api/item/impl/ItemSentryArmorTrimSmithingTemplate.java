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
public interface ItemSentryArmorTrimSmithingTemplate extends ItemStack {
  ItemType<ItemSentryArmorTrimSmithingTemplate> TYPE = ItemTypeBuilder
          .builder(ItemSentryArmorTrimSmithingTemplate.class)
          .vanillaItem(VanillaItemId.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
