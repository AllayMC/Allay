package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemJungleStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemJungleStairsStackInitializer {
  static void init() {
    ItemTypes.JUNGLE_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemJungleStairsStack.class)
            .vanillaItem(VanillaItemId.JUNGLE_STAIRS)
            .build();
  }
}
