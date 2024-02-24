package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemGlowFrameStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGlowFrameStackInitializer {
  static void init() {
    ItemTypes.GLOW_FRAME_TYPE = ItemTypeBuilder
            .builder(ItemGlowFrameStack.class)
            .vanillaItem(VanillaItemId.GLOW_FRAME)
            .build();
  }
}
