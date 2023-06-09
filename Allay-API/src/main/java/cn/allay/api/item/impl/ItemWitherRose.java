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
public interface ItemWitherRose extends ItemStack {
  ItemType<ItemWitherRose> TYPE = ItemTypeBuilder
          .builder(ItemWitherRose.class)
          .vanillaItem(VanillaItemId.WITHER_ROSE, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
