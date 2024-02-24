package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemPolarBearSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolarBearSpawnEggStackInitializer {
  static void init() {
    ItemTypes.POLAR_BEAR_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemPolarBearSpawnEggStack.class)
            .vanillaItem(VanillaItemId.POLAR_BEAR_SPAWN_EGG)
            .build();
  }
}
