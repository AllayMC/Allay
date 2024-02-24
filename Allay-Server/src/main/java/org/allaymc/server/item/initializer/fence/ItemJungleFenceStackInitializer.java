package org.allaymc.server.item.initializer.fence;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.fence.ItemJungleFenceStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemJungleFenceStackInitializer {
  static void init() {
    ItemTypes.JUNGLE_FENCE_TYPE = ItemTypeBuilder
            .builder(ItemJungleFenceStack.class)
            .vanillaItem(VanillaItemId.JUNGLE_FENCE)
            .build();
  }
}
