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
public interface ItemGoldenHoe extends ItemStack {
  ItemType<ItemGoldenHoe> TYPE = ItemTypeBuilder
          .builder(ItemGoldenHoe.class)
          .vanillaItem(VanillaItemId.GOLDEN_HOE, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
