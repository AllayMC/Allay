package org.allaymc.server.item.initializer.wallsign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wallsign.ItemDarkoakWallSignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDarkoakWallSignStackInitializer {
  static void init() {
    ItemTypes.DARKOAK_WALL_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemDarkoakWallSignStack.class)
            .vanillaItem(VanillaItemId.DARKOAK_WALL_SIGN)
            .build();
  }
}
