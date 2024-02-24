package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemPandaSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPandaSpawnEggStackInitializer {
  static void init() {
    ItemTypes.PANDA_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemPandaSpawnEggStack.class)
            .vanillaItem(VanillaItemId.PANDA_SPAWN_EGG)
            .build();
  }
}
