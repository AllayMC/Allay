package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemDolphinSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDolphinSpawnEggStackInitializer {
  static void init() {
    ItemTypes.DOLPHIN_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemDolphinSpawnEggStack.class)
            .vanillaItem(VanillaItemId.DOLPHIN_SPAWN_EGG)
            .build();
  }
}
