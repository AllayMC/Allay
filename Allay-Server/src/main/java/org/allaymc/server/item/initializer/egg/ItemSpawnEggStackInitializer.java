package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSpawnEggStackInitializer {
  static void init() {
    ItemTypes.SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemSpawnEggStack.class)
            .vanillaItem(VanillaItemId.SPAWN_EGG)
            .build();
  }
}
