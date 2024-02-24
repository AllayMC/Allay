package org.allaymc.server.item.initializer.boat;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.boat.ItemDarkOakChestBoatStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDarkOakChestBoatStackInitializer {
  static void init() {
    ItemTypes.DARK_OAK_CHEST_BOAT_TYPE = ItemTypeBuilder
            .builder(ItemDarkOakChestBoatStack.class)
            .vanillaItem(VanillaItemId.DARK_OAK_CHEST_BOAT)
            .build();
  }
}
