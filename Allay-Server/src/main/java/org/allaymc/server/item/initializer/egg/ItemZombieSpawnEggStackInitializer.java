package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemZombieSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemZombieSpawnEggStackInitializer {
  static void init() {
    ItemTypes.ZOMBIE_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemZombieSpawnEggStack.class)
            .vanillaItem(VanillaItemId.ZOMBIE_SPAWN_EGG)
            .build();
  }
}
