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
public interface ItemHeartOfTheSea extends ItemStack {
  ItemType<ItemHeartOfTheSea> TYPE = ItemTypeBuilder
          .builder(ItemHeartOfTheSea.class)
          .vanillaItem(VanillaItemId.HEART_OF_THE_SEA, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
