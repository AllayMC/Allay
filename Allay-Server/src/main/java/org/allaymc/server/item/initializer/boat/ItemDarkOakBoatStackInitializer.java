package org.allaymc.server.item.initializer.boat;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.boat.ItemDarkOakBoatStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDarkOakBoatStackInitializer {
  static void init() {
    ItemTypes.DARK_OAK_BOAT_TYPE = ItemTypeBuilder
            .builder(ItemDarkOakBoatStack.class)
            .vanillaItem(VanillaItemId.DARK_OAK_BOAT)
            .build();
  }
}
