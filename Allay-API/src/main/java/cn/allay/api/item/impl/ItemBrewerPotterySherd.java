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
public interface ItemBrewerPotterySherd extends ItemStack {
  ItemType<ItemBrewerPotterySherd> TYPE = ItemTypeBuilder
          .builder(ItemBrewerPotterySherd.class)
          .vanillaItem(VanillaItemId.BREWER_POTTERY_SHERD, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
