package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemCherryStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherryStairsStackInitializer {
  static void init() {
    ItemTypes.CHERRY_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemCherryStairsStack.class)
            .vanillaItem(VanillaItemId.CHERRY_STAIRS)
            .build();
  }
}
