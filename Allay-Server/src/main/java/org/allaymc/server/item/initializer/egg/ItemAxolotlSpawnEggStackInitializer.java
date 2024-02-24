package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemAxolotlSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAxolotlSpawnEggStackInitializer {
  static void init() {
    ItemTypes.AXOLOTL_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemAxolotlSpawnEggStack.class)
            .vanillaItem(VanillaItemId.AXOLOTL_SPAWN_EGG)
            .build();
  }
}
