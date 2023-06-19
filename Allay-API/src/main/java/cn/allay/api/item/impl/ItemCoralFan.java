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
public interface ItemCoralFan extends ItemStack {
  ItemType<ItemCoralFan> TYPE = ItemTypeBuilder
          .builder(ItemCoralFan.class)
          .vanillaItem(VanillaItemId.CORAL_FAN, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
