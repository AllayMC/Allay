package org.allaymc.server.item.initializer.wallsign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wallsign.ItemBambooWallSignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooWallSignStackInitializer {
  static void init() {
    ItemTypes.BAMBOO_WALL_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemBambooWallSignStack.class)
            .vanillaItem(VanillaItemId.BAMBOO_WALL_SIGN)
            .build();
  }
}
