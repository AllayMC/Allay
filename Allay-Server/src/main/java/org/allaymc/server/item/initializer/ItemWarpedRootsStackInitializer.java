package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemWarpedRootsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedRootsStackInitializer {
  static void init() {
    ItemTypes.WARPED_ROOTS_TYPE = ItemTypeBuilder
            .builder(ItemWarpedRootsStack.class)
            .vanillaItem(VanillaItemId.WARPED_ROOTS)
            .build();
  }
}
