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
public interface ItemGoldOre extends ItemStack {
  ItemType<ItemGoldOre> TYPE = ItemTypeBuilder
          .builder(ItemGoldOre.class)
          .vanillaItem(VanillaItemId.GOLD_ORE, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
