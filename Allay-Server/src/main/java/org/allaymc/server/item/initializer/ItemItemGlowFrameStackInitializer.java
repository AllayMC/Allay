package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemItemGlowFrameStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemGlowFrameStackInitializer {
  static void init() {
    ItemTypes.ITEM_GLOW_FRAME_TYPE = ItemTypeBuilder
            .builder(ItemItemGlowFrameStack.class)
            .vanillaItem(VanillaItemId.ITEM_GLOW_FRAME)
            .build();
  }
}
