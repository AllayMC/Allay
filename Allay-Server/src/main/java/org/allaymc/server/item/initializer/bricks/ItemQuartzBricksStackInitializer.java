package org.allaymc.server.item.initializer.bricks;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.bricks.ItemQuartzBricksStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemQuartzBricksStackInitializer {
  static void init() {
    ItemTypes.QUARTZ_BRICKS_TYPE = ItemTypeBuilder
            .builder(ItemQuartzBricksStack.class)
            .vanillaItem(VanillaItemId.QUARTZ_BRICKS)
            .build();
  }
}
