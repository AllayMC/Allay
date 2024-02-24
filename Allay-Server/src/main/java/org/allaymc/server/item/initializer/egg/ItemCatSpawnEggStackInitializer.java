package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemCatSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCatSpawnEggStackInitializer {
  static void init() {
    ItemTypes.CAT_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemCatSpawnEggStack.class)
            .vanillaItem(VanillaItemId.CAT_SPAWN_EGG)
            .build();
  }
}
