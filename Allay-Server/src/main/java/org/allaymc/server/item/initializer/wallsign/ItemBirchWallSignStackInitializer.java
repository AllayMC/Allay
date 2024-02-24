package org.allaymc.server.item.initializer.wallsign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wallsign.ItemBirchWallSignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBirchWallSignStackInitializer {
  static void init() {
    ItemTypes.BIRCH_WALL_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemBirchWallSignStack.class)
            .vanillaItem(VanillaItemId.BIRCH_WALL_SIGN)
            .build();
  }
}
