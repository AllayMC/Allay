package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemPolishedBlackstoneStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedBlackstoneStairsStackInitializer {
  static void init() {
    ItemTypes.POLISHED_BLACKSTONE_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemPolishedBlackstoneStairsStack.class)
            .vanillaItem(VanillaItemId.POLISHED_BLACKSTONE_STAIRS)
            .build();
  }
}
