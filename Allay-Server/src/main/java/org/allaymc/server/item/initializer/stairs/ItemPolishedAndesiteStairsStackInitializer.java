package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemPolishedAndesiteStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedAndesiteStairsStackInitializer {
  static void init() {
    ItemTypes.POLISHED_ANDESITE_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemPolishedAndesiteStairsStack.class)
            .vanillaItem(VanillaItemId.POLISHED_ANDESITE_STAIRS)
            .build();
  }
}
