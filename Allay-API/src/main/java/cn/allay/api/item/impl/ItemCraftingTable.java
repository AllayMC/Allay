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
public interface ItemCraftingTable extends ItemStack {
  ItemType<ItemCraftingTable> TYPE = ItemTypeBuilder
          .builder(ItemCraftingTable.class)
          .vanillaItem(VanillaItemId.CRAFTING_TABLE, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
