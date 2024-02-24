package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemPhantomSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPhantomSpawnEggStackInitializer {
  static void init() {
    ItemTypes.PHANTOM_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemPhantomSpawnEggStack.class)
            .vanillaItem(VanillaItemId.PHANTOM_SPAWN_EGG)
            .build();
  }
}
