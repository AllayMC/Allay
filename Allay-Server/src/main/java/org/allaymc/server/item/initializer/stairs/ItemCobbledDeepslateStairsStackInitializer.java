package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemCobbledDeepslateStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCobbledDeepslateStairsStackInitializer {
  static void init() {
    ItemTypes.COBBLED_DEEPSLATE_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemCobbledDeepslateStairsStack.class)
            .vanillaItem(VanillaItemId.COBBLED_DEEPSLATE_STAIRS)
            .build();
  }
}
