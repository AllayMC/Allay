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
public interface ItemNautilusShell extends ItemStack {
  ItemType<ItemNautilusShell> TYPE = ItemTypeBuilder
          .builder(ItemNautilusShell.class)
          .vanillaItem(VanillaItemId.NAUTILUS_SHELL, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
