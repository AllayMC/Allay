package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemMossyCobblestoneStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMossyCobblestoneStairsStackInitializer {
  static void init() {
    ItemTypes.MOSSY_COBBLESTONE_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemMossyCobblestoneStairsStack.class)
            .vanillaItem(VanillaItemId.MOSSY_COBBLESTONE_STAIRS)
            .build();
  }
}
