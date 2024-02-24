package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemPolishedGraniteStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedGraniteStairsStackInitializer {
  static void init() {
    ItemTypes.POLISHED_GRANITE_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemPolishedGraniteStairsStack.class)
            .vanillaItem(VanillaItemId.POLISHED_GRANITE_STAIRS)
            .build();
  }
}
