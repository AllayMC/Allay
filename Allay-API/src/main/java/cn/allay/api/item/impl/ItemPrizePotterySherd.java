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
public interface ItemPrizePotterySherd extends ItemStack {
  ItemType<ItemPrizePotterySherd> TYPE = ItemTypeBuilder
          .builder(ItemPrizePotterySherd.class)
          .vanillaItem(VanillaItemId.PRIZE_POTTERY_SHERD, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
