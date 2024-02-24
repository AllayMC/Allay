package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemStriderSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStriderSpawnEggStackInitializer {
  static void init() {
    ItemTypes.STRIDER_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemStriderSpawnEggStack.class)
            .vanillaItem(VanillaItemId.STRIDER_SPAWN_EGG)
            .build();
  }
}
