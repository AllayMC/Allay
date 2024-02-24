package org.allaymc.server.item.initializer.wall;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wall.ItemCobblestoneWallStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCobblestoneWallStackInitializer {
  static void init() {
    ItemTypes.COBBLESTONE_WALL_TYPE = ItemTypeBuilder
            .builder(ItemCobblestoneWallStack.class)
            .vanillaItem(VanillaItemId.COBBLESTONE_WALL)
            .build();
  }
}
