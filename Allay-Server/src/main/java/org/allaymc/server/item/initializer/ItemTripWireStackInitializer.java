package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemTripWireStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTripWireStackInitializer {
  static void init() {
    ItemTypes.TRIP_WIRE_TYPE = ItemTypeBuilder
            .builder(ItemTripWireStack.class)
            .vanillaItem(VanillaItemId.TRIP_WIRE)
            .build();
  }
}
