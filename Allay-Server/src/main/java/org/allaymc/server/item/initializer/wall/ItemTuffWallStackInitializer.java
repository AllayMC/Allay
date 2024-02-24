package org.allaymc.server.item.initializer.wall;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wall.ItemTuffWallStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTuffWallStackInitializer {
  static void init() {
    ItemTypes.TUFF_WALL_TYPE = ItemTypeBuilder
            .builder(ItemTuffWallStack.class)
            .vanillaItem(VanillaItemId.TUFF_WALL)
            .build();
  }
}
