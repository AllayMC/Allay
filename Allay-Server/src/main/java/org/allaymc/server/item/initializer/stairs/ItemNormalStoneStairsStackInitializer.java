package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemNormalStoneStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNormalStoneStairsStackInitializer {
  static void init() {
    ItemTypes.NORMAL_STONE_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemNormalStoneStairsStack.class)
            .vanillaItem(VanillaItemId.NORMAL_STONE_STAIRS)
            .build();
  }
}
