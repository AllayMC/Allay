package org.allaymc.server.item.initializer.fence;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.fence.ItemCrimsonFenceStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrimsonFenceStackInitializer {
  static void init() {
    ItemTypes.CRIMSON_FENCE_TYPE = ItemTypeBuilder
            .builder(ItemCrimsonFenceStack.class)
            .vanillaItem(VanillaItemId.CRIMSON_FENCE)
            .build();
  }
}
