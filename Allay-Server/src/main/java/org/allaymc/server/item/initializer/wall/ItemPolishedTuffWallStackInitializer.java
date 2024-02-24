package org.allaymc.server.item.initializer.wall;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wall.ItemPolishedTuffWallStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedTuffWallStackInitializer {
  static void init() {
    ItemTypes.POLISHED_TUFF_WALL_TYPE = ItemTypeBuilder
            .builder(ItemPolishedTuffWallStack.class)
            .vanillaItem(VanillaItemId.POLISHED_TUFF_WALL)
            .build();
  }
}
