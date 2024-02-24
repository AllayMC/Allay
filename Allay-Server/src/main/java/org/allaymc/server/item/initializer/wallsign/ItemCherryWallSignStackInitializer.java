package org.allaymc.server.item.initializer.wallsign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wallsign.ItemCherryWallSignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherryWallSignStackInitializer {
  static void init() {
    ItemTypes.CHERRY_WALL_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemCherryWallSignStack.class)
            .vanillaItem(VanillaItemId.CHERRY_WALL_SIGN)
            .build();
  }
}
