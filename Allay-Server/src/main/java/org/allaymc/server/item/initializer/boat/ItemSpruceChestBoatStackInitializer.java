package org.allaymc.server.item.initializer.boat;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.boat.ItemSpruceChestBoatStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSpruceChestBoatStackInitializer {
  static void init() {
    ItemTypes.SPRUCE_CHEST_BOAT_TYPE = ItemTypeBuilder
            .builder(ItemSpruceChestBoatStack.class)
            .vanillaItem(VanillaItemId.SPRUCE_CHEST_BOAT)
            .build();
  }
}
