package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemPufferfishSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPufferfishSpawnEggStackInitializer {
  static void init() {
    ItemTypes.PUFFERFISH_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemPufferfishSpawnEggStack.class)
            .vanillaItem(VanillaItemId.PUFFERFISH_SPAWN_EGG)
            .build();
  }
}
