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
public interface ItemNetheriteBoots extends ItemStack {
  ItemType<ItemNetheriteBoots> TYPE = ItemTypeBuilder
          .builder(ItemNetheriteBoots.class)
          .vanillaItem(VanillaItemId.NETHERITE_BOOTS, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
