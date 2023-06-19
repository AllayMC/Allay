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
public interface ItemElement85 extends ItemStack {
  ItemType<ItemElement85> TYPE = ItemTypeBuilder
          .builder(ItemElement85.class)
          .vanillaItem(VanillaItemId.ELEMENT_85, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
