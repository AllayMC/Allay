package org.allaymc.server.item.initializer.wall;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wall.ItemCobbledDeepslateWallStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCobbledDeepslateWallStackInitializer {
  static void init() {
    ItemTypes.COBBLED_DEEPSLATE_WALL_TYPE = ItemTypeBuilder
            .builder(ItemCobbledDeepslateWallStack.class)
            .vanillaItem(VanillaItemId.COBBLED_DEEPSLATE_WALL)
            .build();
  }
}
