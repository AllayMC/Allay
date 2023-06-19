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
public interface ItemPotato extends ItemStack {
  ItemType<ItemPotato> TYPE = ItemTypeBuilder
          .builder(ItemPotato.class)
          .vanillaItem(VanillaItemId.POTATO, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
