package org.allaymc.server.item.initializer.fence;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.fence.ItemAcaciaFenceStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAcaciaFenceStackInitializer {
  static void init() {
    ItemTypes.ACACIA_FENCE_TYPE = ItemTypeBuilder
            .builder(ItemAcaciaFenceStack.class)
            .vanillaItem(VanillaItemId.ACACIA_FENCE)
            .build();
  }
}
