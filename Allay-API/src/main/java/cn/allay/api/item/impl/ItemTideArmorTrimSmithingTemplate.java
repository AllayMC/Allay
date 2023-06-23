package cn.allay.api.item.impl;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;
import cn.allay.api.item.type.ItemTypeRegistry;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTideArmorTrimSmithingTemplate extends ItemStack {
  ItemType<ItemTideArmorTrimSmithingTemplate> TYPE = ItemTypeBuilder
          .builder(ItemTideArmorTrimSmithingTemplate.class)
          .vanillaItem(VanillaItemId.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
