package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemSnowGolemSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSnowGolemSpawnEggStackInitializer {
  static void init() {
    ItemTypes.SNOW_GOLEM_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemSnowGolemSpawnEggStack.class)
            .vanillaItem(VanillaItemId.SNOW_GOLEM_SPAWN_EGG)
            .build();
  }
}
