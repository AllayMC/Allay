package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemBeeSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBeeSpawnEggStackInitializer {
  static void init() {
    ItemTypes.BEE_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemBeeSpawnEggStack.class)
            .vanillaItem(VanillaItemId.BEE_SPAWN_EGG)
            .build();
  }
}
