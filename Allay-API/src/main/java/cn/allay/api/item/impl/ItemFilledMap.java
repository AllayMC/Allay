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
public interface ItemFilledMap extends ItemStack {
  ItemType<ItemFilledMap> TYPE = ItemTypeBuilder
          .builder(ItemFilledMap.class)
          .vanillaItem(VanillaItemId.FILLED_MAP, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
