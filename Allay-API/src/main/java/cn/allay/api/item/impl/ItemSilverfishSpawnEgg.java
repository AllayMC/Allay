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
public interface ItemSilverfishSpawnEgg extends ItemStack {
  ItemType<ItemSilverfishSpawnEgg> TYPE = ItemTypeBuilder
          .builder(ItemSilverfishSpawnEgg.class)
          .vanillaItem(VanillaItemId.SILVERFISH_SPAWN_EGG, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
