package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBasaltStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBasaltStackInitializer {
  static void init() {
    ItemTypes.BASALT_TYPE = ItemTypeBuilder
            .builder(ItemBasaltStack.class)
            .vanillaItem(VanillaItemId.BASALT)
            .build();
  }
}
