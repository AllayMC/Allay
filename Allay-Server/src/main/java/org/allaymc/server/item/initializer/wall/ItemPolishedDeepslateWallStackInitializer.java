package org.allaymc.server.item.initializer.wall;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wall.ItemPolishedDeepslateWallStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedDeepslateWallStackInitializer {
  static void init() {
    ItemTypes.POLISHED_DEEPSLATE_WALL_TYPE = ItemTypeBuilder
            .builder(ItemPolishedDeepslateWallStack.class)
            .vanillaItem(VanillaItemId.POLISHED_DEEPSLATE_WALL)
            .build();
  }
}
