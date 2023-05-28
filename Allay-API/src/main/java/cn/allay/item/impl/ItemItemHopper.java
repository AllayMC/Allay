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
public interface ItemItemHopper extends ItemStack {
  ItemType<ItemItemHopper> TYPE = ItemTypeBuilder
  .builder(ItemItemHopper.class)
  .vanillaItem(VanillaItemId.ITEM_HOPPER, true)
  .addBasicComponents()
  .build().register(ItemTypeRegistry.getRegistry());
}
