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
public interface ItemLightWeightedPressurePlate extends ItemStack {
  ItemType<ItemLightWeightedPressurePlate> TYPE = ItemTypeBuilder
          .builder(ItemLightWeightedPressurePlate.class)
          .vanillaItem(VanillaItemId.LIGHT_WEIGHTED_PRESSURE_PLATE, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
