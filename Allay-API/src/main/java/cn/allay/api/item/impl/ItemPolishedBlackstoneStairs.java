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
public interface ItemPolishedBlackstoneStairs extends ItemStack {
  ItemType<ItemPolishedBlackstoneStairs> TYPE = ItemTypeBuilder
          .builder(ItemPolishedBlackstoneStairs.class)
          .vanillaItem(VanillaItemId.POLISHED_BLACKSTONE_STAIRS, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
