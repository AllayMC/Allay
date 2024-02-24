package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemEvokerSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEvokerSpawnEggStackInitializer {
  static void init() {
    ItemTypes.EVOKER_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemEvokerSpawnEggStack.class)
            .vanillaItem(VanillaItemId.EVOKER_SPAWN_EGG)
            .build();
  }
}
