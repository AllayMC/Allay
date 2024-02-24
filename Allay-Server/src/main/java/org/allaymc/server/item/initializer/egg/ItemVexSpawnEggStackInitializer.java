package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemVexSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemVexSpawnEggStackInitializer {
  static void init() {
    ItemTypes.VEX_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemVexSpawnEggStack.class)
            .vanillaItem(VanillaItemId.VEX_SPAWN_EGG)
            .build();
  }
}
