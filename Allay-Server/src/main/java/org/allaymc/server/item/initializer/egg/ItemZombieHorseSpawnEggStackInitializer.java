package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemZombieHorseSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemZombieHorseSpawnEggStackInitializer {
  static void init() {
    ItemTypes.ZOMBIE_HORSE_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemZombieHorseSpawnEggStack.class)
            .vanillaItem(VanillaItemId.ZOMBIE_HORSE_SPAWN_EGG)
            .build();
  }
}
