package org.allaymc.server.item.initializer.wallsign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wallsign.ItemCrimsonWallSignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrimsonWallSignStackInitializer {
  static void init() {
    ItemTypes.CRIMSON_WALL_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemCrimsonWallSignStack.class)
            .vanillaItem(VanillaItemId.CRIMSON_WALL_SIGN)
            .build();
  }
}
