package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCactusStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCactusStackInitializer {
  static void init() {
    ItemTypes.CACTUS_TYPE = ItemTypeBuilder
            .builder(ItemCactusStack.class)
            .vanillaItem(VanillaItemId.CACTUS)
            .build();
  }
}
