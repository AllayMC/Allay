package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemWolfSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWolfSpawnEggStackInitializer {
  static void init() {
    ItemTypes.WOLF_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemWolfSpawnEggStack.class)
            .vanillaItem(VanillaItemId.WOLF_SPAWN_EGG)
            .build();
  }
}
