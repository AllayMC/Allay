package org.allaymc.server.item.initializer.bricks;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.bricks.ItemCrackedPolishedBlackstoneBricksStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrackedPolishedBlackstoneBricksStackInitializer {
  static void init() {
    ItemTypes.CRACKED_POLISHED_BLACKSTONE_BRICKS_TYPE = ItemTypeBuilder
            .builder(ItemCrackedPolishedBlackstoneBricksStack.class)
            .vanillaItem(VanillaItemId.CRACKED_POLISHED_BLACKSTONE_BRICKS)
            .build();
  }
}
