package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemBrickStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBrickStairsStackInitializer {
  static void init() {
    ItemTypes.BRICK_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemBrickStairsStack.class)
            .vanillaItem(VanillaItemId.BRICK_STAIRS)
            .build();
  }
}
