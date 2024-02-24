package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemIronGolemSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemIronGolemSpawnEggStackInitializer {
  static void init() {
    ItemTypes.IRON_GOLEM_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemIronGolemSpawnEggStack.class)
            .vanillaItem(VanillaItemId.IRON_GOLEM_SPAWN_EGG)
            .build();
  }
}
