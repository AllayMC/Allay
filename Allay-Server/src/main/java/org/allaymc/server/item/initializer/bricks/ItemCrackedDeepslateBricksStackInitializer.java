package org.allaymc.server.item.initializer.bricks;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.bricks.ItemCrackedDeepslateBricksStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrackedDeepslateBricksStackInitializer {
  static void init() {
    ItemTypes.CRACKED_DEEPSLATE_BRICKS_TYPE = ItemTypeBuilder
            .builder(ItemCrackedDeepslateBricksStack.class)
            .vanillaItem(VanillaItemId.CRACKED_DEEPSLATE_BRICKS)
            .build();
  }
}
