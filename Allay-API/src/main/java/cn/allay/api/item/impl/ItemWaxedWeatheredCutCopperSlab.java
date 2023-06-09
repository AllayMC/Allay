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
public interface ItemWaxedWeatheredCutCopperSlab extends ItemStack {
  ItemType<ItemWaxedWeatheredCutCopperSlab> TYPE = ItemTypeBuilder
          .builder(ItemWaxedWeatheredCutCopperSlab.class)
          .vanillaItem(VanillaItemId.WAXED_WEATHERED_CUT_COPPER_SLAB, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
