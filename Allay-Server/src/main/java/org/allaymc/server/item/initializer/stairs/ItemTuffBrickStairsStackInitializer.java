package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemTuffBrickStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTuffBrickStairsStackInitializer {
  static void init() {
    ItemTypes.TUFF_BRICK_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemTuffBrickStairsStack.class)
            .vanillaItem(VanillaItemId.TUFF_BRICK_STAIRS)
            .build();
  }
}
