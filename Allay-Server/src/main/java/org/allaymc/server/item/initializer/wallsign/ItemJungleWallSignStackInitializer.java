package org.allaymc.server.item.initializer.wallsign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wallsign.ItemJungleWallSignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemJungleWallSignStackInitializer {
  static void init() {
    ItemTypes.JUNGLE_WALL_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemJungleWallSignStack.class)
            .vanillaItem(VanillaItemId.JUNGLE_WALL_SIGN)
            .build();
  }
}
