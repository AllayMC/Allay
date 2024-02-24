package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemWitherSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWitherSpawnEggStackInitializer {
  static void init() {
    ItemTypes.WITHER_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemWitherSpawnEggStack.class)
            .vanillaItem(VanillaItemId.WITHER_SPAWN_EGG)
            .build();
  }
}
