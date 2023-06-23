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
public interface ItemPolishedAndesiteStairs extends ItemStack {
  ItemType<ItemPolishedAndesiteStairs> TYPE = ItemTypeBuilder
          .builder(ItemPolishedAndesiteStairs.class)
          .vanillaItem(VanillaItemId.POLISHED_ANDESITE_STAIRS, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
