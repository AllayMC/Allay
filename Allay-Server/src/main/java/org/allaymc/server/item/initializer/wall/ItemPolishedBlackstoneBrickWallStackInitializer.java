package org.allaymc.server.item.initializer.wall;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wall.ItemPolishedBlackstoneBrickWallStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedBlackstoneBrickWallStackInitializer {
  static void init() {
    ItemTypes.POLISHED_BLACKSTONE_BRICK_WALL_TYPE = ItemTypeBuilder
            .builder(ItemPolishedBlackstoneBrickWallStack.class)
            .vanillaItem(VanillaItemId.POLISHED_BLACKSTONE_BRICK_WALL)
            .build();
  }
}
