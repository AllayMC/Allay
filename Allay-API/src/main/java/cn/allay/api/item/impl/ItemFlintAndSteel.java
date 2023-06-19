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
public interface ItemFlintAndSteel extends ItemStack {
  ItemType<ItemFlintAndSteel> TYPE = ItemTypeBuilder
          .builder(ItemFlintAndSteel.class)
          .vanillaItem(VanillaItemId.FLINT_AND_STEEL, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
