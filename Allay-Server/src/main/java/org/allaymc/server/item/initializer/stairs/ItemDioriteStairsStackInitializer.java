package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemDioriteStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDioriteStairsStackInitializer {
  static void init() {
    ItemTypes.DIORITE_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemDioriteStairsStack.class)
            .vanillaItem(VanillaItemId.DIORITE_STAIRS)
            .build();
  }
}
