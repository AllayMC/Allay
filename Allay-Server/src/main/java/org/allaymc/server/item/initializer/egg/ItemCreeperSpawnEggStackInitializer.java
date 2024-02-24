package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemCreeperSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCreeperSpawnEggStackInitializer {
  static void init() {
    ItemTypes.CREEPER_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemCreeperSpawnEggStack.class)
            .vanillaItem(VanillaItemId.CREEPER_SPAWN_EGG)
            .build();
  }
}
