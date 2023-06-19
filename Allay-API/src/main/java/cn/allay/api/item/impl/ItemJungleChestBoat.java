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
public interface ItemJungleChestBoat extends ItemStack {
  ItemType<ItemJungleChestBoat> TYPE = ItemTypeBuilder
          .builder(ItemJungleChestBoat.class)
          .vanillaItem(VanillaItemId.JUNGLE_CHEST_BOAT, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
