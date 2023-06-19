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
public interface ItemArmsUpPotterySherd extends ItemStack {
  ItemType<ItemArmsUpPotterySherd> TYPE = ItemTypeBuilder
          .builder(ItemArmsUpPotterySherd.class)
          .vanillaItem(VanillaItemId.ARMS_UP_POTTERY_SHERD, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
