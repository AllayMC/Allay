package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemPolishedDeepslateStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedDeepslateStairsStackInitializer {
  static void init() {
    ItemTypes.POLISHED_DEEPSLATE_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemPolishedDeepslateStairsStack.class)
            .vanillaItem(VanillaItemId.POLISHED_DEEPSLATE_STAIRS)
            .build();
  }
}
