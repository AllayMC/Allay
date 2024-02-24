package org.allaymc.server.item.initializer.boat;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.boat.ItemCherryBoatStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherryBoatStackInitializer {
  static void init() {
    ItemTypes.CHERRY_BOAT_TYPE = ItemTypeBuilder
            .builder(ItemCherryBoatStack.class)
            .vanillaItem(VanillaItemId.CHERRY_BOAT)
            .build();
  }
}
