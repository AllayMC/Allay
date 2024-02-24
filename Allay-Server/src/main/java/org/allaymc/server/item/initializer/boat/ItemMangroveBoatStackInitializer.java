package org.allaymc.server.item.initializer.boat;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.boat.ItemMangroveBoatStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMangroveBoatStackInitializer {
  static void init() {
    ItemTypes.MANGROVE_BOAT_TYPE = ItemTypeBuilder
            .builder(ItemMangroveBoatStack.class)
            .vanillaItem(VanillaItemId.MANGROVE_BOAT)
            .build();
  }
}
