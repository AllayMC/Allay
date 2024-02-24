package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemCodSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCodSpawnEggStackInitializer {
  static void init() {
    ItemTypes.COD_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemCodSpawnEggStack.class)
            .vanillaItem(VanillaItemId.COD_SPAWN_EGG)
            .build();
  }
}
