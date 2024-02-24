package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemPolishedTuffStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedTuffStairsStackInitializer {
  static void init() {
    ItemTypes.POLISHED_TUFF_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemPolishedTuffStairsStack.class)
            .vanillaItem(VanillaItemId.POLISHED_TUFF_STAIRS)
            .build();
  }
}
