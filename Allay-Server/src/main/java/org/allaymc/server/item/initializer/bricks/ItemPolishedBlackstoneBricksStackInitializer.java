package org.allaymc.server.item.initializer.bricks;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.bricks.ItemPolishedBlackstoneBricksStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedBlackstoneBricksStackInitializer {
  static void init() {
    ItemTypes.POLISHED_BLACKSTONE_BRICKS_TYPE = ItemTypeBuilder
            .builder(ItemPolishedBlackstoneBricksStack.class)
            .vanillaItem(VanillaItemId.POLISHED_BLACKSTONE_BRICKS)
            .build();
  }
}
