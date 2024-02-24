package org.allaymc.server.item.initializer.boat;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.boat.ItemJungleBoatStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemJungleBoatStackInitializer {
  static void init() {
    ItemTypes.JUNGLE_BOAT_TYPE = ItemTypeBuilder
            .builder(ItemJungleBoatStack.class)
            .vanillaItem(VanillaItemId.JUNGLE_BOAT)
            .build();
  }
}
