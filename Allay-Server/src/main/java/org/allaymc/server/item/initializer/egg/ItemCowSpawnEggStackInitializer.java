package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemCowSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCowSpawnEggStackInitializer {
  static void init() {
    ItemTypes.COW_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemCowSpawnEggStack.class)
            .vanillaItem(VanillaItemId.COW_SPAWN_EGG)
            .build();
  }
}
