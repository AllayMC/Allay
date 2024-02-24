package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemSilverfishSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSilverfishSpawnEggStackInitializer {
  static void init() {
    ItemTypes.SILVERFISH_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemSilverfishSpawnEggStack.class)
            .vanillaItem(VanillaItemId.SILVERFISH_SPAWN_EGG)
            .build();
  }
}
