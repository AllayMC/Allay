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
public interface ItemFishingRod extends ItemStack {
  ItemType<ItemFishingRod> TYPE = ItemTypeBuilder
          .builder(ItemFishingRod.class)
          .vanillaItem(VanillaItemId.FISHING_ROD, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
