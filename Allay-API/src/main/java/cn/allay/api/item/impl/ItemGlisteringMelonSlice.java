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
public interface ItemGlisteringMelonSlice extends ItemStack {
  ItemType<ItemGlisteringMelonSlice> TYPE = ItemTypeBuilder
          .builder(ItemGlisteringMelonSlice.class)
          .vanillaItem(VanillaItemId.GLISTERING_MELON_SLICE, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
