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
public interface ItemHeavyWeightedPressurePlate extends ItemStack {
  ItemType<ItemHeavyWeightedPressurePlate> TYPE = ItemTypeBuilder
          .builder(ItemHeavyWeightedPressurePlate.class)
          .vanillaItem(VanillaItemId.HEAVY_WEIGHTED_PRESSURE_PLATE, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
