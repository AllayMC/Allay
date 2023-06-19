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
public interface ItemLeaves2 extends ItemStack {
  ItemType<ItemLeaves2> TYPE = ItemTypeBuilder
          .builder(ItemLeaves2.class)
          .vanillaItem(VanillaItemId.LEAVES2, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
