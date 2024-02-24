package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemCrimsonStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrimsonStairsStackInitializer {
  static void init() {
    ItemTypes.CRIMSON_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemCrimsonStairsStack.class)
            .vanillaItem(VanillaItemId.CRIMSON_STAIRS)
            .build();
  }
}
