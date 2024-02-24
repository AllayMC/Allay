package org.allaymc.server.item.initializer.wallsign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wallsign.ItemMangroveWallSignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMangroveWallSignStackInitializer {
  static void init() {
    ItemTypes.MANGROVE_WALL_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemMangroveWallSignStack.class)
            .vanillaItem(VanillaItemId.MANGROVE_WALL_SIGN)
            .build();
  }
}
