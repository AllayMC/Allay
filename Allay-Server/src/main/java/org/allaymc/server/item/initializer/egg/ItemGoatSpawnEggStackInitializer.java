package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemGoatSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGoatSpawnEggStackInitializer {
  static void init() {
    ItemTypes.GOAT_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemGoatSpawnEggStack.class)
            .vanillaItem(VanillaItemId.GOAT_SPAWN_EGG)
            .build();
  }
}
