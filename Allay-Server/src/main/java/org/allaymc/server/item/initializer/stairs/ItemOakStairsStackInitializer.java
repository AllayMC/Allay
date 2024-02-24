package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemOakStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOakStairsStackInitializer {
  static void init() {
    ItemTypes.OAK_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemOakStairsStack.class)
            .vanillaItem(VanillaItemId.OAK_STAIRS)
            .build();
  }
}
