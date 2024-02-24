package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemPrismarineStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPrismarineStairsStackInitializer {
  static void init() {
    ItemTypes.PRISMARINE_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemPrismarineStairsStack.class)
            .vanillaItem(VanillaItemId.PRISMARINE_STAIRS)
            .build();
  }
}
