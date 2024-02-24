package org.allaymc.server.item.initializer.boat;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.boat.ItemJungleChestBoatStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemJungleChestBoatStackInitializer {
  static void init() {
    ItemTypes.JUNGLE_CHEST_BOAT_TYPE = ItemTypeBuilder
            .builder(ItemJungleChestBoatStack.class)
            .vanillaItem(VanillaItemId.JUNGLE_CHEST_BOAT)
            .build();
  }
}
