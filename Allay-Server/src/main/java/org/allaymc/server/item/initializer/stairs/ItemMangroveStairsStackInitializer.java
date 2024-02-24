package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemMangroveStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMangroveStairsStackInitializer {
  static void init() {
    ItemTypes.MANGROVE_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemMangroveStairsStack.class)
            .vanillaItem(VanillaItemId.MANGROVE_STAIRS)
            .build();
  }
}
