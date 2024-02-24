package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemVillagerSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemVillagerSpawnEggStackInitializer {
  static void init() {
    ItemTypes.VILLAGER_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemVillagerSpawnEggStack.class)
            .vanillaItem(VanillaItemId.VILLAGER_SPAWN_EGG)
            .build();
  }
}
