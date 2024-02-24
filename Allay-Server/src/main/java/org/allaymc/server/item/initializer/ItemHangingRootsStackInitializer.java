package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemHangingRootsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHangingRootsStackInitializer {
  static void init() {
    ItemTypes.HANGING_ROOTS_TYPE = ItemTypeBuilder
            .builder(ItemHangingRootsStack.class)
            .vanillaItem(VanillaItemId.HANGING_ROOTS)
            .build();
  }
}
