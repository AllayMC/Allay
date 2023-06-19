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
public interface ItemDeepslateGoldOre extends ItemStack {
  ItemType<ItemDeepslateGoldOre> TYPE = ItemTypeBuilder
          .builder(ItemDeepslateGoldOre.class)
          .vanillaItem(VanillaItemId.DEEPSLATE_GOLD_ORE, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
