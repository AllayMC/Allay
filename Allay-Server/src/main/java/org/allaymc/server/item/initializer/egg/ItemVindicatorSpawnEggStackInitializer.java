package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemVindicatorSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemVindicatorSpawnEggStackInitializer {
  static void init() {
    ItemTypes.VINDICATOR_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemVindicatorSpawnEggStack.class)
            .vanillaItem(VanillaItemId.VINDICATOR_SPAWN_EGG)
            .build();
  }
}
