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
public interface ItemDeepslateIronOre extends ItemStack {
  ItemType<ItemDeepslateIronOre> TYPE = ItemTypeBuilder
          .builder(ItemDeepslateIronOre.class)
          .vanillaItem(VanillaItemId.DEEPSLATE_IRON_ORE, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
