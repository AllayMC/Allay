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
public interface ItemMangroveRoots extends ItemStack {
  ItemType<ItemMangroveRoots> TYPE = ItemTypeBuilder
          .builder(ItemMangroveRoots.class)
          .vanillaItem(VanillaItemId.MANGROVE_ROOTS, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
