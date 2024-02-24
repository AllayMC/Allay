package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemBlackstoneStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlackstoneStairsStackInitializer {
  static void init() {
    ItemTypes.BLACKSTONE_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemBlackstoneStairsStack.class)
            .vanillaItem(VanillaItemId.BLACKSTONE_STAIRS)
            .build();
  }
}
