package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemRedNetherBrickStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedNetherBrickStairsStackInitializer {
  static void init() {
    ItemTypes.RED_NETHER_BRICK_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemRedNetherBrickStairsStack.class)
            .vanillaItem(VanillaItemId.RED_NETHER_BRICK_STAIRS)
            .build();
  }
}
