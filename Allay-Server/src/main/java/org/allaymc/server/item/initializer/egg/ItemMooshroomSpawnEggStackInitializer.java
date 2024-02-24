package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemMooshroomSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMooshroomSpawnEggStackInitializer {
  static void init() {
    ItemTypes.MOOSHROOM_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemMooshroomSpawnEggStack.class)
            .vanillaItem(VanillaItemId.MOOSHROOM_SPAWN_EGG)
            .build();
  }
}
