package org.allaymc.server.item.initializer.wallsign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wallsign.ItemWallSignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWallSignStackInitializer {
  static void init() {
    ItemTypes.WALL_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemWallSignStack.class)
            .vanillaItem(VanillaItemId.WALL_SIGN)
            .build();
  }
}
