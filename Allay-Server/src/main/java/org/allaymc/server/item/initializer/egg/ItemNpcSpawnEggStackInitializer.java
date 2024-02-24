package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemNpcSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNpcSpawnEggStackInitializer {
  static void init() {
    ItemTypes.NPC_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemNpcSpawnEggStack.class)
            .vanillaItem(VanillaItemId.NPC_SPAWN_EGG)
            .build();
  }
}
