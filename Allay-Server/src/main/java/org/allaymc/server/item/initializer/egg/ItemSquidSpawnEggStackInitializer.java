package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemSquidSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSquidSpawnEggStackInitializer {
  static void init() {
    ItemTypes.SQUID_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemSquidSpawnEggStack.class)
            .vanillaItem(VanillaItemId.SQUID_SPAWN_EGG)
            .build();
  }
}
