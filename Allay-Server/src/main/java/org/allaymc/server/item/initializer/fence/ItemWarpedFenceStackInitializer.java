package org.allaymc.server.item.initializer.fence;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.fence.ItemWarpedFenceStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedFenceStackInitializer {
  static void init() {
    ItemTypes.WARPED_FENCE_TYPE = ItemTypeBuilder
            .builder(ItemWarpedFenceStack.class)
            .vanillaItem(VanillaItemId.WARPED_FENCE)
            .build();
  }
}
