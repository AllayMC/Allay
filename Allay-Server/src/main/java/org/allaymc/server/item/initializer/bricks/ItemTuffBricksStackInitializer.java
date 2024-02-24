package org.allaymc.server.item.initializer.bricks;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.bricks.ItemTuffBricksStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTuffBricksStackInitializer {
  static void init() {
    ItemTypes.TUFF_BRICKS_TYPE = ItemTypeBuilder
            .builder(ItemTuffBricksStack.class)
            .vanillaItem(VanillaItemId.TUFF_BRICKS)
            .build();
  }
}
