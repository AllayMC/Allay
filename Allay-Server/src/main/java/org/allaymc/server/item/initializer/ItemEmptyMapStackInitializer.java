package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemEmptyMapStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEmptyMapStackInitializer {
  static void init() {
    ItemTypes.EMPTY_MAP_TYPE = ItemTypeBuilder
            .builder(ItemEmptyMapStack.class)
            .vanillaItem(VanillaItemId.EMPTY_MAP)
            .build();
  }
}
