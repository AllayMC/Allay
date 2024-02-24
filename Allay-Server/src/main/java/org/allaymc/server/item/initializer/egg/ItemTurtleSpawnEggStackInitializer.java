package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemTurtleSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTurtleSpawnEggStackInitializer {
  static void init() {
    ItemTypes.TURTLE_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemTurtleSpawnEggStack.class)
            .vanillaItem(VanillaItemId.TURTLE_SPAWN_EGG)
            .build();
  }
}
