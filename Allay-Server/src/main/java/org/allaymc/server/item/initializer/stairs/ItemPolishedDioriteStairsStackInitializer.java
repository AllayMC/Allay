package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemPolishedDioriteStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedDioriteStairsStackInitializer {
  static void init() {
    ItemTypes.POLISHED_DIORITE_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemPolishedDioriteStairsStack.class)
            .vanillaItem(VanillaItemId.POLISHED_DIORITE_STAIRS)
            .build();
  }
}
