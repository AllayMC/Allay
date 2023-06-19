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
public interface ItemElement10 extends ItemStack {
  ItemType<ItemElement10> TYPE = ItemTypeBuilder
          .builder(ItemElement10.class)
          .vanillaItem(VanillaItemId.ELEMENT_10, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
