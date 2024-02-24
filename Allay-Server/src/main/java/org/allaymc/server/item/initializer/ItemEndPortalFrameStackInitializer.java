package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemEndPortalFrameStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEndPortalFrameStackInitializer {
  static void init() {
    ItemTypes.END_PORTAL_FRAME_TYPE = ItemTypeBuilder
            .builder(ItemEndPortalFrameStack.class)
            .vanillaItem(VanillaItemId.END_PORTAL_FRAME)
            .build();
  }
}
