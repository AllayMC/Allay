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
public interface ItemCryingObsidian extends ItemStack {
  ItemType<ItemCryingObsidian> TYPE = ItemTypeBuilder
          .builder(ItemCryingObsidian.class)
          .vanillaItem(VanillaItemId.CRYING_OBSIDIAN, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
