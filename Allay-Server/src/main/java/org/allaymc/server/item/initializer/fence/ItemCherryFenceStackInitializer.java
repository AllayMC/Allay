package org.allaymc.server.item.initializer.fence;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.fence.ItemCherryFenceStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherryFenceStackInitializer {
  static void init() {
    ItemTypes.CHERRY_FENCE_TYPE = ItemTypeBuilder
            .builder(ItemCherryFenceStack.class)
            .vanillaItem(VanillaItemId.CHERRY_FENCE)
            .build();
  }
}
