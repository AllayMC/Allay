package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemWarpedStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedStairsStackInitializer {
  static void init() {
    ItemTypes.WARPED_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemWarpedStairsStack.class)
            .vanillaItem(VanillaItemId.WARPED_STAIRS)
            .build();
  }
}
