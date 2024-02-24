package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemEndermanSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEndermanSpawnEggStackInitializer {
  static void init() {
    ItemTypes.ENDERMAN_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemEndermanSpawnEggStack.class)
            .vanillaItem(VanillaItemId.ENDERMAN_SPAWN_EGG)
            .build();
  }
}
