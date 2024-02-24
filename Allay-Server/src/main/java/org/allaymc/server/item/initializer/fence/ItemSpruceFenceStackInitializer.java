package org.allaymc.server.item.initializer.fence;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.fence.ItemSpruceFenceStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSpruceFenceStackInitializer {
  static void init() {
    ItemTypes.SPRUCE_FENCE_TYPE = ItemTypeBuilder
            .builder(ItemSpruceFenceStack.class)
            .vanillaItem(VanillaItemId.SPRUCE_FENCE)
            .build();
  }
}
