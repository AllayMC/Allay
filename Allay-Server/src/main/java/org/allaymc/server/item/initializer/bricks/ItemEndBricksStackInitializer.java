package org.allaymc.server.item.initializer.bricks;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.bricks.ItemEndBricksStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEndBricksStackInitializer {
  static void init() {
    ItemTypes.END_BRICKS_TYPE = ItemTypeBuilder
            .builder(ItemEndBricksStack.class)
            .vanillaItem(VanillaItemId.END_BRICKS)
            .build();
  }
}
