package org.allaymc.server.item.initializer.fence;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.fence.ItemFenceStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFenceStackInitializer {
  static void init() {
    ItemTypes.FENCE_TYPE = ItemTypeBuilder
            .builder(ItemFenceStack.class)
            .vanillaItem(VanillaItemId.FENCE)
            .build();
  }
}
