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
public interface ItemElement4 extends ItemStack {
  ItemType<ItemElement4> TYPE = ItemTypeBuilder
          .builder(ItemElement4.class)
          .vanillaItem(VanillaItemId.ELEMENT_4, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
