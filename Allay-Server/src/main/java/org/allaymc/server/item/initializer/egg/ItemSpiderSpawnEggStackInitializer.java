package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemSpiderSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSpiderSpawnEggStackInitializer {
  static void init() {
    ItemTypes.SPIDER_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemSpiderSpawnEggStack.class)
            .vanillaItem(VanillaItemId.SPIDER_SPAWN_EGG)
            .build();
  }
}
