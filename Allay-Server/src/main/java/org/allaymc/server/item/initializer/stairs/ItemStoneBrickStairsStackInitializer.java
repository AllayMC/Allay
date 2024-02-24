package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemStoneBrickStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStoneBrickStairsStackInitializer {
  static void init() {
    ItemTypes.STONE_BRICK_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemStoneBrickStairsStack.class)
            .vanillaItem(VanillaItemId.STONE_BRICK_STAIRS)
            .build();
  }
}
