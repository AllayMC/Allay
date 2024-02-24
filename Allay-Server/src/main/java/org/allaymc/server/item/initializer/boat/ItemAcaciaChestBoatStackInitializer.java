package org.allaymc.server.item.initializer.boat;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.boat.ItemAcaciaChestBoatStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAcaciaChestBoatStackInitializer {
  static void init() {
    ItemTypes.ACACIA_CHEST_BOAT_TYPE = ItemTypeBuilder
            .builder(ItemAcaciaChestBoatStack.class)
            .vanillaItem(VanillaItemId.ACACIA_CHEST_BOAT)
            .build();
  }
}
