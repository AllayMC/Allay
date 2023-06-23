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
public interface ItemWheatSeeds extends ItemStack {
  ItemType<ItemWheatSeeds> TYPE = ItemTypeBuilder
          .builder(ItemWheatSeeds.class)
          .vanillaItem(VanillaItemId.WHEAT_SEEDS, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
