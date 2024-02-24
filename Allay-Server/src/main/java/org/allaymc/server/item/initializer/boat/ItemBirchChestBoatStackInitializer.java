package org.allaymc.server.item.initializer.boat;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.boat.ItemBirchChestBoatStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBirchChestBoatStackInitializer {
  static void init() {
    ItemTypes.BIRCH_CHEST_BOAT_TYPE = ItemTypeBuilder
            .builder(ItemBirchChestBoatStack.class)
            .vanillaItem(VanillaItemId.BIRCH_CHEST_BOAT)
            .build();
  }
}
