package org.allaymc.server.item.initializer.fence;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.fence.ItemBirchFenceStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBirchFenceStackInitializer {
  static void init() {
    ItemTypes.BIRCH_FENCE_TYPE = ItemTypeBuilder
            .builder(ItemBirchFenceStack.class)
            .vanillaItem(VanillaItemId.BIRCH_FENCE)
            .build();
  }
}
