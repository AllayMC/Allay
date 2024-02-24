package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemStoneStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStoneStairsStackInitializer {
  static void init() {
    ItemTypes.STONE_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemStoneStairsStack.class)
            .vanillaItem(VanillaItemId.STONE_STAIRS)
            .build();
  }
}
