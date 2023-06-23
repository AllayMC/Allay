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
public interface ItemSpireArmorTrimSmithingTemplate extends ItemStack {
  ItemType<ItemSpireArmorTrimSmithingTemplate> TYPE = ItemTypeBuilder
          .builder(ItemSpireArmorTrimSmithingTemplate.class)
          .vanillaItem(VanillaItemId.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
