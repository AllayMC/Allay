package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemParrotSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemParrotSpawnEggStackInitializer {
  static void init() {
    ItemTypes.PARROT_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemParrotSpawnEggStack.class)
            .vanillaItem(VanillaItemId.PARROT_SPAWN_EGG)
            .build();
  }
}
