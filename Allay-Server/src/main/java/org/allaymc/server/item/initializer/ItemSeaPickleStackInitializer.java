package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSeaPickleStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSeaPickleStackInitializer {
  static void init() {
    ItemTypes.SEA_PICKLE_TYPE = ItemTypeBuilder
            .builder(ItemSeaPickleStack.class)
            .vanillaItem(VanillaItemId.SEA_PICKLE)
            .build();
  }
}
