package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemTadpoleSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTadpoleSpawnEggStackInitializer {
  static void init() {
    ItemTypes.TADPOLE_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemTadpoleSpawnEggStack.class)
            .vanillaItem(VanillaItemId.TADPOLE_SPAWN_EGG)
            .build();
  }
}
