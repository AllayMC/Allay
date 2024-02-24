package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemItemFrameStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemFrameStackInitializer {
  static void init() {
    ItemTypes.ITEM_FRAME_TYPE = ItemTypeBuilder
            .builder(ItemItemFrameStack.class)
            .vanillaItem(VanillaItemId.ITEM_FRAME)
            .build();
  }
}
