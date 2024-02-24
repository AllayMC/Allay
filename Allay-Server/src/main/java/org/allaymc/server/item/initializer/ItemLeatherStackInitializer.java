package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemLeatherStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLeatherStackInitializer {
  static void init() {
    ItemTypes.LEATHER_TYPE = ItemTypeBuilder
            .builder(ItemLeatherStack.class)
            .vanillaItem(VanillaItemId.LEATHER)
            .build();
  }
}
