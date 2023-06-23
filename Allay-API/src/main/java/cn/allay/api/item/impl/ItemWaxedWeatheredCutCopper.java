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
public interface ItemWaxedWeatheredCutCopper extends ItemStack {
  ItemType<ItemWaxedWeatheredCutCopper> TYPE = ItemTypeBuilder
          .builder(ItemWaxedWeatheredCutCopper.class)
          .vanillaItem(VanillaItemId.WAXED_WEATHERED_CUT_COPPER, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
