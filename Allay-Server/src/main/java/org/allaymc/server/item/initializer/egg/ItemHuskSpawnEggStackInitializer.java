package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemHuskSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHuskSpawnEggStackInitializer {
  static void init() {
    ItemTypes.HUSK_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemHuskSpawnEggStack.class)
            .vanillaItem(VanillaItemId.HUSK_SPAWN_EGG)
            .build();
  }
}
