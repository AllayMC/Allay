package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemStraySpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStraySpawnEggStackInitializer {
  static void init() {
    ItemTypes.STRAY_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemStraySpawnEggStack.class)
            .vanillaItem(VanillaItemId.STRAY_SPAWN_EGG)
            .build();
  }
}
