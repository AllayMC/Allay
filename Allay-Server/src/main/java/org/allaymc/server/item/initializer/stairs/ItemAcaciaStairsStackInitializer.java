package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemAcaciaStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAcaciaStairsStackInitializer {
  static void init() {
    ItemTypes.ACACIA_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemAcaciaStairsStack.class)
            .vanillaItem(VanillaItemId.ACACIA_STAIRS)
            .build();
  }
}
