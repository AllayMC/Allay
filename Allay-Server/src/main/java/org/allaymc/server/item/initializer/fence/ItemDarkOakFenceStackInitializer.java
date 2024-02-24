package org.allaymc.server.item.initializer.fence;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.fence.ItemDarkOakFenceStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDarkOakFenceStackInitializer {
  static void init() {
    ItemTypes.DARK_OAK_FENCE_TYPE = ItemTypeBuilder
            .builder(ItemDarkOakFenceStack.class)
            .vanillaItem(VanillaItemId.DARK_OAK_FENCE)
            .build();
  }
}
