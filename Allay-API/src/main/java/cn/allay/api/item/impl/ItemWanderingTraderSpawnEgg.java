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
public interface ItemWanderingTraderSpawnEgg extends ItemStack {
  ItemType<ItemWanderingTraderSpawnEgg> TYPE = ItemTypeBuilder
          .builder(ItemWanderingTraderSpawnEgg.class)
          .vanillaItem(VanillaItemId.WANDERING_TRADER_SPAWN_EGG, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
