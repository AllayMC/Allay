package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemTuffStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTuffStairsStackInitializer {
  static void init() {
    ItemTypes.TUFF_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemTuffStairsStack.class)
            .vanillaItem(VanillaItemId.TUFF_STAIRS)
            .build();
  }
}
