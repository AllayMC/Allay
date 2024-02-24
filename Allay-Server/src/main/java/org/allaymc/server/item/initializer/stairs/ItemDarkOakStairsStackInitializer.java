package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemDarkOakStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDarkOakStairsStackInitializer {
  static void init() {
    ItemTypes.DARK_OAK_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemDarkOakStairsStack.class)
            .vanillaItem(VanillaItemId.DARK_OAK_STAIRS)
            .build();
  }
}
