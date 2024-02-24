package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemMobSpawnerStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMobSpawnerStackInitializer {
  static void init() {
    ItemTypes.MOB_SPAWNER_TYPE = ItemTypeBuilder
            .builder(ItemMobSpawnerStack.class)
            .vanillaItem(VanillaItemId.MOB_SPAWNER)
            .build();
  }
}
