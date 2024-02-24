package org.allaymc.server.item.initializer.fence;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.fence.ItemMangroveFenceStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMangroveFenceStackInitializer {
  static void init() {
    ItemTypes.MANGROVE_FENCE_TYPE = ItemTypeBuilder
            .builder(ItemMangroveFenceStack.class)
            .vanillaItem(VanillaItemId.MANGROVE_FENCE)
            .build();
  }
}
