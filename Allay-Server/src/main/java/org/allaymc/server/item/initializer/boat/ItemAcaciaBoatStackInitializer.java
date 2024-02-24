package org.allaymc.server.item.initializer.boat;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.boat.ItemAcaciaBoatStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAcaciaBoatStackInitializer {
  static void init() {
    ItemTypes.ACACIA_BOAT_TYPE = ItemTypeBuilder
            .builder(ItemAcaciaBoatStack.class)
            .vanillaItem(VanillaItemId.ACACIA_BOAT)
            .build();
  }
}
