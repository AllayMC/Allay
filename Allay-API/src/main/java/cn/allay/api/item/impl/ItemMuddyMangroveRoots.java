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
public interface ItemMuddyMangroveRoots extends ItemStack {
  ItemType<ItemMuddyMangroveRoots> TYPE = ItemTypeBuilder
          .builder(ItemMuddyMangroveRoots.class)
          .vanillaItem(VanillaItemId.MUDDY_MANGROVE_ROOTS, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
