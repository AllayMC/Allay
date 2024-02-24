package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemGraniteStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGraniteStairsStackInitializer {
  static void init() {
    ItemTypes.GRANITE_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemGraniteStairsStack.class)
            .vanillaItem(VanillaItemId.GRANITE_STAIRS)
            .build();
  }
}
