package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemWardenSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWardenSpawnEggStackInitializer {
  static void init() {
    ItemTypes.WARDEN_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemWardenSpawnEggStack.class)
            .vanillaItem(VanillaItemId.WARDEN_SPAWN_EGG)
            .build();
  }
}
