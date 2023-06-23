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
public interface ItemWitherSpawnEgg extends ItemStack {
  ItemType<ItemWitherSpawnEgg> TYPE = ItemTypeBuilder
          .builder(ItemWitherSpawnEgg.class)
          .vanillaItem(VanillaItemId.WITHER_SPAWN_EGG, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
