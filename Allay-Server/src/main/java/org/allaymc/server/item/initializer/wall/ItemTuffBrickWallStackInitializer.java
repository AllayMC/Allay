package org.allaymc.server.item.initializer.wall;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wall.ItemTuffBrickWallStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTuffBrickWallStackInitializer {
  static void init() {
    ItemTypes.TUFF_BRICK_WALL_TYPE = ItemTypeBuilder
            .builder(ItemTuffBrickWallStack.class)
            .vanillaItem(VanillaItemId.TUFF_BRICK_WALL)
            .build();
  }
}
