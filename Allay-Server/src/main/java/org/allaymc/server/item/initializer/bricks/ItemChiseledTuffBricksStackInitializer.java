package org.allaymc.server.item.initializer.bricks;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.bricks.ItemChiseledTuffBricksStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChiseledTuffBricksStackInitializer {
  static void init() {
    ItemTypes.CHISELED_TUFF_BRICKS_TYPE = ItemTypeBuilder
            .builder(ItemChiseledTuffBricksStack.class)
            .vanillaItem(VanillaItemId.CHISELED_TUFF_BRICKS)
            .build();
  }
}
