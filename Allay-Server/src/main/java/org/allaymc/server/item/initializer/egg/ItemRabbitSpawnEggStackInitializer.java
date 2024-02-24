package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemRabbitSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRabbitSpawnEggStackInitializer {
  static void init() {
    ItemTypes.RABBIT_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemRabbitSpawnEggStack.class)
            .vanillaItem(VanillaItemId.RABBIT_SPAWN_EGG)
            .build();
  }
}
