package org.allaymc.server.item.initializer.fence;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.fence.ItemNetherBrickFenceStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetherBrickFenceStackInitializer {
  static void init() {
    ItemTypes.NETHER_BRICK_FENCE_TYPE = ItemTypeBuilder
            .builder(ItemNetherBrickFenceStack.class)
            .vanillaItem(VanillaItemId.NETHER_BRICK_FENCE)
            .build();
  }
}
