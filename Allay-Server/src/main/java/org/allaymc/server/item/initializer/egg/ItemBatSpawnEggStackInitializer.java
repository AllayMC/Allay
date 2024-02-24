package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemBatSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBatSpawnEggStackInitializer {
  static void init() {
    ItemTypes.BAT_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemBatSpawnEggStack.class)
            .vanillaItem(VanillaItemId.BAT_SPAWN_EGG)
            .build();
  }
}
