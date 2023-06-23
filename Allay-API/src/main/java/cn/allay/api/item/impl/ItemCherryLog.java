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
public interface ItemCherryLog extends ItemStack {
  ItemType<ItemCherryLog> TYPE = ItemTypeBuilder
          .builder(ItemCherryLog.class)
          .vanillaItem(VanillaItemId.CHERRY_LOG, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
