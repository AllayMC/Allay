package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemPillagerSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPillagerSpawnEggStackInitializer {
  static void init() {
    ItemTypes.PILLAGER_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemPillagerSpawnEggStack.class)
            .vanillaItem(VanillaItemId.PILLAGER_SPAWN_EGG)
            .build();
  }
}
