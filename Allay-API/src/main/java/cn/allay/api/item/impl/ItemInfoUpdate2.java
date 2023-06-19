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
public interface ItemInfoUpdate2 extends ItemStack {
  ItemType<ItemInfoUpdate2> TYPE = ItemTypeBuilder
          .builder(ItemInfoUpdate2.class)
          .vanillaItem(VanillaItemId.INFO_UPDATE2, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
