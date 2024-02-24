package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemRavagerSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRavagerSpawnEggStackInitializer {
  static void init() {
    ItemTypes.RAVAGER_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemRavagerSpawnEggStack.class)
            .vanillaItem(VanillaItemId.RAVAGER_SPAWN_EGG)
            .build();
  }
}
