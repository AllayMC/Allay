package org.allaymc.server.item.initializer.boat;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.boat.ItemOakChestBoatStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOakChestBoatStackInitializer {
  static void init() {
    ItemTypes.OAK_CHEST_BOAT_TYPE = ItemTypeBuilder
            .builder(ItemOakChestBoatStack.class)
            .vanillaItem(VanillaItemId.OAK_CHEST_BOAT)
            .build();
  }
}
