package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemHorseSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHorseSpawnEggStackInitializer {
  static void init() {
    ItemTypes.HORSE_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemHorseSpawnEggStack.class)
            .vanillaItem(VanillaItemId.HORSE_SPAWN_EGG)
            .build();
  }
}
