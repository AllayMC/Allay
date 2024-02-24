package org.allaymc.server.item.initializer.wall;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wall.ItemBlackstoneWallStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlackstoneWallStackInitializer {
  static void init() {
    ItemTypes.BLACKSTONE_WALL_TYPE = ItemTypeBuilder
            .builder(ItemBlackstoneWallStack.class)
            .vanillaItem(VanillaItemId.BLACKSTONE_WALL)
            .build();
  }
}
