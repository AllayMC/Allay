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
public interface ItemMangroveWallSign extends ItemStack {
  ItemType<ItemMangroveWallSign> TYPE = ItemTypeBuilder
          .builder(ItemMangroveWallSign.class)
          .vanillaItem(VanillaItemId.MANGROVE_WALL_SIGN, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
