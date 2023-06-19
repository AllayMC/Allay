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
public interface ItemLectern extends ItemStack {
  ItemType<ItemLectern> TYPE = ItemTypeBuilder
          .builder(ItemLectern.class)
          .vanillaItem(VanillaItemId.LECTERN, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
