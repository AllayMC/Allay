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
public interface ItemCrackedNetherBricks extends ItemStack {
  ItemType<ItemCrackedNetherBricks> TYPE = ItemTypeBuilder
          .builder(ItemCrackedNetherBricks.class)
          .vanillaItem(VanillaItemId.CRACKED_NETHER_BRICKS, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
