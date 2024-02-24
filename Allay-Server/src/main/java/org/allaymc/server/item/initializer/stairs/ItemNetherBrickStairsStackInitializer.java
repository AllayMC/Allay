package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemNetherBrickStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetherBrickStairsStackInitializer {
  static void init() {
    ItemTypes.NETHER_BRICK_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemNetherBrickStairsStack.class)
            .vanillaItem(VanillaItemId.NETHER_BRICK_STAIRS)
            .build();
  }
}
