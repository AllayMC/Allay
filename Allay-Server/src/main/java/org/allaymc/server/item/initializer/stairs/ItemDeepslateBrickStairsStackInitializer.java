package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemDeepslateBrickStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeepslateBrickStairsStackInitializer {
  static void init() {
    ItemTypes.DEEPSLATE_BRICK_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemDeepslateBrickStairsStack.class)
            .vanillaItem(VanillaItemId.DEEPSLATE_BRICK_STAIRS)
            .build();
  }
}
