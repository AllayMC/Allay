package org.allaymc.server.item.initializer.wall;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wall.ItemPolishedBlackstoneWallStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedBlackstoneWallStackInitializer {
  static void init() {
    ItemTypes.POLISHED_BLACKSTONE_WALL_TYPE = ItemTypeBuilder
            .builder(ItemPolishedBlackstoneWallStack.class)
            .vanillaItem(VanillaItemId.POLISHED_BLACKSTONE_WALL)
            .build();
  }
}
