package org.allaymc.server.item.initializer.fence;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.fence.ItemOakFenceStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOakFenceStackInitializer {
  static void init() {
    ItemTypes.OAK_FENCE_TYPE = ItemTypeBuilder
            .builder(ItemOakFenceStack.class)
            .vanillaItem(VanillaItemId.OAK_FENCE)
            .build();
  }
}
