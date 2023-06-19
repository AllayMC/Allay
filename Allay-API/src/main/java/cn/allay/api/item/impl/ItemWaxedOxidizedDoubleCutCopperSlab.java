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
public interface ItemWaxedOxidizedDoubleCutCopperSlab extends ItemStack {
  ItemType<ItemWaxedOxidizedDoubleCutCopperSlab> TYPE = ItemTypeBuilder
          .builder(ItemWaxedOxidizedDoubleCutCopperSlab.class)
          .vanillaItem(VanillaItemId.WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
