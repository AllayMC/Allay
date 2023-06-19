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
public interface ItemJungleTrapdoor extends ItemStack {
  ItemType<ItemJungleTrapdoor> TYPE = ItemTypeBuilder
          .builder(ItemJungleTrapdoor.class)
          .vanillaItem(VanillaItemId.JUNGLE_TRAPDOOR, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
