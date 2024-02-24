package org.allaymc.server.item.initializer.wallsign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wallsign.ItemWarpedWallSignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedWallSignStackInitializer {
  static void init() {
    ItemTypes.WARPED_WALL_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemWarpedWallSignStack.class)
            .vanillaItem(VanillaItemId.WARPED_WALL_SIGN)
            .build();
  }
}
