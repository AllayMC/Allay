package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemArmadilloSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemArmadilloSpawnEggStackInitializer {
  static void init() {
    ItemTypes.ARMADILLO_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemArmadilloSpawnEggStack.class)
            .vanillaItem(VanillaItemId.ARMADILLO_SPAWN_EGG)
            .build();
  }
}
