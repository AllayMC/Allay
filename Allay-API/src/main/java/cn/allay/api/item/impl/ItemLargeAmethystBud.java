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
public interface ItemLargeAmethystBud extends ItemStack {
  ItemType<ItemLargeAmethystBud> TYPE = ItemTypeBuilder
          .builder(ItemLargeAmethystBud.class)
          .vanillaItem(VanillaItemId.LARGE_AMETHYST_BUD, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
