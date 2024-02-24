package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemCaveSpiderSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCaveSpiderSpawnEggStackInitializer {
  static void init() {
    ItemTypes.CAVE_SPIDER_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemCaveSpiderSpawnEggStack.class)
            .vanillaItem(VanillaItemId.CAVE_SPIDER_SPAWN_EGG)
            .build();
  }
}
