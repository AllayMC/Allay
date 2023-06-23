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
public interface ItemFriendPotterySherd extends ItemStack {
  ItemType<ItemFriendPotterySherd> TYPE = ItemTypeBuilder
          .builder(ItemFriendPotterySherd.class)
          .vanillaItem(VanillaItemId.FRIEND_POTTERY_SHERD, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
