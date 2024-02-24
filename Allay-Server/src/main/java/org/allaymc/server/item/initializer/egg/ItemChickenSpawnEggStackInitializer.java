package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemChickenSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChickenSpawnEggStackInitializer {
  static void init() {
    ItemTypes.CHICKEN_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemChickenSpawnEggStack.class)
            .vanillaItem(VanillaItemId.CHICKEN_SPAWN_EGG)
            .build();
  }
}
