package org.allaymc.server.item.initializer.wallsign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wallsign.ItemAcaciaWallSignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAcaciaWallSignStackInitializer {
  static void init() {
    ItemTypes.ACACIA_WALL_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemAcaciaWallSignStack.class)
            .vanillaItem(VanillaItemId.ACACIA_WALL_SIGN)
            .build();
  }
}
