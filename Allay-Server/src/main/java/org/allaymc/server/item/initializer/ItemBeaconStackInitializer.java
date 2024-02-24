package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBeaconStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBeaconStackInitializer {
  static void init() {
    ItemTypes.BEACON_TYPE = ItemTypeBuilder
            .builder(ItemBeaconStack.class)
            .vanillaItem(VanillaItemId.BEACON)
            .build();
  }
}
