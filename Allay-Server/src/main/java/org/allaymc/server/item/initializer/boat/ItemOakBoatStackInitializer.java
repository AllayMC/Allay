package org.allaymc.server.item.initializer.boat;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.boat.ItemOakBoatStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOakBoatStackInitializer {
  static void init() {
    ItemTypes.OAK_BOAT_TYPE = ItemTypeBuilder
            .builder(ItemOakBoatStack.class)
            .vanillaItem(VanillaItemId.OAK_BOAT)
            .build();
  }
}
