package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemAllaySpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAllaySpawnEggStackInitializer {
  static void init() {
    ItemTypes.ALLAY_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemAllaySpawnEggStack.class)
            .vanillaItem(VanillaItemId.ALLAY_SPAWN_EGG)
            .build();
  }
}
