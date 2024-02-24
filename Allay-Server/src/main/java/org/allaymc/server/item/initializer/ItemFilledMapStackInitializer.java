package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemFilledMapStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFilledMapStackInitializer {
  static void init() {
    ItemTypes.FILLED_MAP_TYPE = ItemTypeBuilder
            .builder(ItemFilledMapStack.class)
            .vanillaItem(VanillaItemId.FILLED_MAP)
            .build();
  }
}
