package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemSnifferSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSnifferSpawnEggStackInitializer {
  static void init() {
    ItemTypes.SNIFFER_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemSnifferSpawnEggStack.class)
            .vanillaItem(VanillaItemId.SNIFFER_SPAWN_EGG)
            .build();
  }
}
