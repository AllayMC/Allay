package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemZoglinSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemZoglinSpawnEggStackInitializer {
  static void init() {
    ItemTypes.ZOGLIN_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemZoglinSpawnEggStack.class)
            .vanillaItem(VanillaItemId.ZOGLIN_SPAWN_EGG)
            .build();
  }
}
