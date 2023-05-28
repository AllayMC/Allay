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
public interface ItemItemSoulCampfire extends ItemStack {
  ItemType<ItemItemSoulCampfire> TYPE = ItemTypeBuilder
  .builder(ItemItemSoulCampfire.class)
  .vanillaItem(VanillaItemId.ITEM_SOUL_CAMPFIRE, true)
  .addBasicComponents()
  .build().register(ItemTypeRegistry.getRegistry());
}
