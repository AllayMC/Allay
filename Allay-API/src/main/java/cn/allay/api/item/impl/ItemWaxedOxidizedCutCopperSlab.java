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
public interface ItemWaxedOxidizedCutCopperSlab extends ItemStack {
  ItemType<ItemWaxedOxidizedCutCopperSlab> TYPE = ItemTypeBuilder
          .builder(ItemWaxedOxidizedCutCopperSlab.class)
          .vanillaItem(VanillaItemId.WAXED_OXIDIZED_CUT_COPPER_SLAB, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
