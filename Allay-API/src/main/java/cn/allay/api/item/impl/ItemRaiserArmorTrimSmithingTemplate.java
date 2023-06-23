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
public interface ItemRaiserArmorTrimSmithingTemplate extends ItemStack {
  ItemType<ItemRaiserArmorTrimSmithingTemplate> TYPE = ItemTypeBuilder
          .builder(ItemRaiserArmorTrimSmithingTemplate.class)
          .vanillaItem(VanillaItemId.RAISER_ARMOR_TRIM_SMITHING_TEMPLATE, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
