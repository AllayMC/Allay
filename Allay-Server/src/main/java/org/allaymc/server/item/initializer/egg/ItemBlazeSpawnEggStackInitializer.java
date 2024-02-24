package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemBlazeSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlazeSpawnEggStackInitializer {
  static void init() {
    ItemTypes.BLAZE_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemBlazeSpawnEggStack.class)
            .vanillaItem(VanillaItemId.BLAZE_SPAWN_EGG)
            .build();
  }
}
