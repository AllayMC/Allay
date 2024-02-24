package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemCamelSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCamelSpawnEggStackInitializer {
  static void init() {
    ItemTypes.CAMEL_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemCamelSpawnEggStack.class)
            .vanillaItem(VanillaItemId.CAMEL_SPAWN_EGG)
            .build();
  }
}
