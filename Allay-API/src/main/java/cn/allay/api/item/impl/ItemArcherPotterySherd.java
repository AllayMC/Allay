package cn.allay.api.item.impl;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;
import cn.allay.api.item.type.ItemTypeRegistry;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemArcherPotterySherd extends ItemStack {
  ItemType<ItemArcherPotterySherd> TYPE = ItemTypeBuilder
          .builder(ItemArcherPotterySherd.class)
          .vanillaItem(VanillaItemId.ARCHER_POTTERY_SHERD, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
