package org.allaymc.server.item.initializer.wallsign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wallsign.ItemSpruceWallSignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSpruceWallSignStackInitializer {
  static void init() {
    ItemTypes.SPRUCE_WALL_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemSpruceWallSignStack.class)
            .vanillaItem(VanillaItemId.SPRUCE_WALL_SIGN)
            .build();
  }
}
