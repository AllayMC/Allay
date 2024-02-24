package org.allaymc.server.item.initializer.bricks;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.bricks.ItemChiseledNetherBricksStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChiseledNetherBricksStackInitializer {
  static void init() {
    ItemTypes.CHISELED_NETHER_BRICKS_TYPE = ItemTypeBuilder
            .builder(ItemChiseledNetherBricksStack.class)
            .vanillaItem(VanillaItemId.CHISELED_NETHER_BRICKS)
            .build();
  }
}
