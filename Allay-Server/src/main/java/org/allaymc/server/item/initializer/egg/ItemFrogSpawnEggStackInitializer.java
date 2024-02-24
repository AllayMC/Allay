package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemFrogSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFrogSpawnEggStackInitializer {
  static void init() {
    ItemTypes.FROG_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemFrogSpawnEggStack.class)
            .vanillaItem(VanillaItemId.FROG_SPAWN_EGG)
            .build();
  }
}
