package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemMudBrickStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMudBrickStairsStackInitializer {
  static void init() {
    ItemTypes.MUD_BRICK_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemMudBrickStairsStack.class)
            .vanillaItem(VanillaItemId.MUD_BRICK_STAIRS)
            .build();
  }
}
