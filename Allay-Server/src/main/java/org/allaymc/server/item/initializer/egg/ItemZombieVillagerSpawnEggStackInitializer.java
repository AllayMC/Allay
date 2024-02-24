package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemZombieVillagerSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemZombieVillagerSpawnEggStackInitializer {
  static void init() {
    ItemTypes.ZOMBIE_VILLAGER_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemZombieVillagerSpawnEggStack.class)
            .vanillaItem(VanillaItemId.ZOMBIE_VILLAGER_SPAWN_EGG)
            .build();
  }
}
