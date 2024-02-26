package org.allaymc.server.item.initializer.boat;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.boat.ItemSpruceBoatStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSpruceBoatStackInitializer {
  static void init() {
    ItemTypes.SPRUCE_BOAT_TYPE = ItemTypeBuilder
            .builder(ItemSpruceBoatStack.class)
            .vanillaItem(VanillaItemId.SPRUCE_BOAT)
            .build();
  }
}