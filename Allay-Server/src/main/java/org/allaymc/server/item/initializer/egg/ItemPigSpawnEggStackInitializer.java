package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemPigSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPigSpawnEggStackInitializer {
  static void init() {
    ItemTypes.PIG_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemPigSpawnEggStack.class)
            .vanillaItem(VanillaItemId.PIG_SPAWN_EGG)
            .build();
  }
}
