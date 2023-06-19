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
public interface ItemElement98 extends ItemStack {
  ItemType<ItemElement98> TYPE = ItemTypeBuilder
          .builder(ItemElement98.class)
          .vanillaItem(VanillaItemId.ELEMENT_98, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
