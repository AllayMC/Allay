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
public interface ItemWaxedOxidizedCutCopperStairs extends ItemStack {
  ItemType<ItemWaxedOxidizedCutCopperStairs> TYPE = ItemTypeBuilder
          .builder(ItemWaxedOxidizedCutCopperStairs.class)
          .vanillaItem(VanillaItemId.WAXED_OXIDIZED_CUT_COPPER_STAIRS, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
