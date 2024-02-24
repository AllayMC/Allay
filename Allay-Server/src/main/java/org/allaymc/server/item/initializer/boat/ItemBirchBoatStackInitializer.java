package org.allaymc.server.item.initializer.boat;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.boat.ItemBirchBoatStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBirchBoatStackInitializer {
  static void init() {
    ItemTypes.BIRCH_BOAT_TYPE = ItemTypeBuilder
            .builder(ItemBirchBoatStack.class)
            .vanillaItem(VanillaItemId.BIRCH_BOAT)
            .build();
  }
}
