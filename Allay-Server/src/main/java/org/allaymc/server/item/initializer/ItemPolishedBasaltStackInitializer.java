package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPolishedBasaltStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedBasaltStackInitializer {
  static void init() {
    ItemTypes.POLISHED_BASALT_TYPE = ItemTypeBuilder
            .builder(ItemPolishedBasaltStack.class)
            .vanillaItem(VanillaItemId.POLISHED_BASALT)
            .build();
  }
}
