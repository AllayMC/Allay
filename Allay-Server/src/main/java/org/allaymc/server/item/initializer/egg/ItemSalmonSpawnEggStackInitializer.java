package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemSalmonSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSalmonSpawnEggStackInitializer {
  static void init() {
    ItemTypes.SALMON_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemSalmonSpawnEggStack.class)
            .vanillaItem(VanillaItemId.SALMON_SPAWN_EGG)
            .build();
  }
}
