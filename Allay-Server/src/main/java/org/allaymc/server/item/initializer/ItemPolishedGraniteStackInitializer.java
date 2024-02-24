package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPolishedGraniteStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedGraniteStackInitializer {
  static void init() {
    ItemTypes.POLISHED_GRANITE_TYPE = ItemTypeBuilder
            .builder(ItemPolishedGraniteStack.class)
            .vanillaItem(VanillaItemId.POLISHED_GRANITE)
            .build();
  }
}
