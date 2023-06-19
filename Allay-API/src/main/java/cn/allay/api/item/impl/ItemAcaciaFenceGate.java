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
public interface ItemAcaciaFenceGate extends ItemStack {
  ItemType<ItemAcaciaFenceGate> TYPE = ItemTypeBuilder
          .builder(ItemAcaciaFenceGate.class)
          .vanillaItem(VanillaItemId.ACACIA_FENCE_GATE, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
