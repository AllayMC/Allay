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
public interface ItemDeadBrainCoral extends ItemStack {
  ItemType<ItemDeadBrainCoral> TYPE = ItemTypeBuilder
          .builder(ItemDeadBrainCoral.class)
          .vanillaItem(VanillaItemId.DEAD_BRAIN_CORAL, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
