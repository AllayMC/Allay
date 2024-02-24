package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemFrameStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFrameStackInitializer {
  static void init() {
    ItemTypes.FRAME_TYPE = ItemTypeBuilder
            .builder(ItemFrameStack.class)
            .vanillaItem(VanillaItemId.FRAME)
            .build();
  }
}
