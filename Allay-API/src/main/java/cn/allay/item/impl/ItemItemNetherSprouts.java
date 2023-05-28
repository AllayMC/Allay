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
public interface ItemItemNetherSprouts extends ItemStack {
  ItemType<ItemItemNetherSprouts> TYPE = ItemTypeBuilder
  .builder(ItemItemNetherSprouts.class)
  .vanillaItem(VanillaItemId.ITEM_NETHER_SPROUTS, true)
  .addBasicComponents()
  .build().register(ItemTypeRegistry.getRegistry());
}
