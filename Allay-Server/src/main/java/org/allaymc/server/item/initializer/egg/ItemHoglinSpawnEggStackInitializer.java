package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemHoglinSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHoglinSpawnEggStackInitializer {
  static void init() {
    ItemTypes.HOGLIN_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemHoglinSpawnEggStack.class)
            .vanillaItem(VanillaItemId.HOGLIN_SPAWN_EGG)
            .build();
  }
}
