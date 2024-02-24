package org.allaymc.server.item.initializer.bricks;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.bricks.ItemMudBricksStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMudBricksStackInitializer {
  static void init() {
    ItemTypes.MUD_BRICKS_TYPE = ItemTypeBuilder
            .builder(ItemMudBricksStack.class)
            .vanillaItem(VanillaItemId.MUD_BRICKS)
            .build();
  }
}
