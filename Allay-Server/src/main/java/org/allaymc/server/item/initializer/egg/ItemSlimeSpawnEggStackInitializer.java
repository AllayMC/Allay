package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemSlimeSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSlimeSpawnEggStackInitializer {
  static void init() {
    ItemTypes.SLIME_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemSlimeSpawnEggStack.class)
            .vanillaItem(VanillaItemId.SLIME_SPAWN_EGG)
            .build();
  }
}
