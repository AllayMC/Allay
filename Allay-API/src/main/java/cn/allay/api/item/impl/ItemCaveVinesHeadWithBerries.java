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
public interface ItemCaveVinesHeadWithBerries extends ItemStack {
  ItemType<ItemCaveVinesHeadWithBerries> TYPE = ItemTypeBuilder
          .builder(ItemCaveVinesHeadWithBerries.class)
          .vanillaItem(VanillaItemId.CAVE_VINES_HEAD_WITH_BERRIES, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
