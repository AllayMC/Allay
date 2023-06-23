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
public interface ItemEvokerSpawnEgg extends ItemStack {
  ItemType<ItemEvokerSpawnEgg> TYPE = ItemTypeBuilder
          .builder(ItemEvokerSpawnEgg.class)
          .vanillaItem(VanillaItemId.EVOKER_SPAWN_EGG, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
