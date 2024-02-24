package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemMuleSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMuleSpawnEggStackInitializer {
  static void init() {
    ItemTypes.MULE_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemMuleSpawnEggStack.class)
            .vanillaItem(VanillaItemId.MULE_SPAWN_EGG)
            .build();
  }
}
