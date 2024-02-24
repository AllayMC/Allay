package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemPiglinSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPiglinSpawnEggStackInitializer {
  static void init() {
    ItemTypes.PIGLIN_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemPiglinSpawnEggStack.class)
            .vanillaItem(VanillaItemId.PIGLIN_SPAWN_EGG)
            .build();
  }
}
