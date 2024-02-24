package org.allaymc.server.item.initializer.fence;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.fence.ItemBambooFenceStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooFenceStackInitializer {
  static void init() {
    ItemTypes.BAMBOO_FENCE_TYPE = ItemTypeBuilder
            .builder(ItemBambooFenceStack.class)
            .vanillaItem(VanillaItemId.BAMBOO_FENCE)
            .build();
  }
}
