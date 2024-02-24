package org.allaymc.server.item.initializer.boat;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.boat.ItemBoatStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBoatStackInitializer {
  static void init() {
    ItemTypes.BOAT_TYPE = ItemTypeBuilder
            .builder(ItemBoatStack.class)
            .vanillaItem(VanillaItemId.BOAT)
            .build();
  }
}
