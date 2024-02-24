package org.allaymc.server.item.initializer.bricks;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.bricks.ItemCrackedNetherBricksStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrackedNetherBricksStackInitializer {
  static void init() {
    ItemTypes.CRACKED_NETHER_BRICKS_TYPE = ItemTypeBuilder
            .builder(ItemCrackedNetherBricksStack.class)
            .vanillaItem(VanillaItemId.CRACKED_NETHER_BRICKS)
            .build();
  }
}
