package org.allaymc.server.item.initializer.bricks;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.bricks.ItemDeepslateBricksStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeepslateBricksStackInitializer {
  static void init() {
    ItemTypes.DEEPSLATE_BRICKS_TYPE = ItemTypeBuilder
            .builder(ItemDeepslateBricksStack.class)
            .vanillaItem(VanillaItemId.DEEPSLATE_BRICKS)
            .build();
  }
}
