package org.allaymc.server.item.initializer.boat;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.boat.ItemChestBoatStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChestBoatStackInitializer {
  static void init() {
    ItemTypes.CHEST_BOAT_TYPE = ItemTypeBuilder
            .builder(ItemChestBoatStack.class)
            .vanillaItem(VanillaItemId.CHEST_BOAT)
            .build();
  }
}
