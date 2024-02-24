package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemZombiePigmanSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemZombiePigmanSpawnEggStackInitializer {
  static void init() {
    ItemTypes.ZOMBIE_PIGMAN_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemZombiePigmanSpawnEggStack.class)
            .vanillaItem(VanillaItemId.ZOMBIE_PIGMAN_SPAWN_EGG)
            .build();
  }
}
