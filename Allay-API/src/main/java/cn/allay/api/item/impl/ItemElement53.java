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
public interface ItemElement53 extends ItemStack {
  ItemType<ItemElement53> TYPE = ItemTypeBuilder
          .builder(ItemElement53.class)
          .vanillaItem(VanillaItemId.ELEMENT_53, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
