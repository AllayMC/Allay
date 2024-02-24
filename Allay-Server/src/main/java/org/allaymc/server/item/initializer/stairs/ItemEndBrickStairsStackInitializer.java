package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemEndBrickStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEndBrickStairsStackInitializer {
  static void init() {
    ItemTypes.END_BRICK_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemEndBrickStairsStack.class)
            .vanillaItem(VanillaItemId.END_BRICK_STAIRS)
            .build();
  }
}
