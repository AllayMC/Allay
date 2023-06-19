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
public interface ItemNetheriteShovel extends ItemStack {
  ItemType<ItemNetheriteShovel> TYPE = ItemTypeBuilder
          .builder(ItemNetheriteShovel.class)
          .vanillaItem(VanillaItemId.NETHERITE_SHOVEL, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
