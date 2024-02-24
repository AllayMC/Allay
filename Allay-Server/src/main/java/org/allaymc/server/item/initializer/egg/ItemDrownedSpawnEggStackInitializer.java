package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemDrownedSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDrownedSpawnEggStackInitializer {
  static void init() {
    ItemTypes.DROWNED_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemDrownedSpawnEggStack.class)
            .vanillaItem(VanillaItemId.DROWNED_SPAWN_EGG)
            .build();
  }
}
