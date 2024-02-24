package org.allaymc.server.item.initializer.boat;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.boat.ItemMangroveChestBoatStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMangroveChestBoatStackInitializer {
  static void init() {
    ItemTypes.MANGROVE_CHEST_BOAT_TYPE = ItemTypeBuilder
            .builder(ItemMangroveChestBoatStack.class)
            .vanillaItem(VanillaItemId.MANGROVE_CHEST_BOAT)
            .build();
  }
}
