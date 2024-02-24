package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemFoxSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFoxSpawnEggStackInitializer {
  static void init() {
    ItemTypes.FOX_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemFoxSpawnEggStack.class)
            .vanillaItem(VanillaItemId.FOX_SPAWN_EGG)
            .build();
  }
}
