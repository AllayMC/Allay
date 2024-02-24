package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemWarpedNyliumStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedNyliumStackInitializer {
  static void init() {
    ItemTypes.WARPED_NYLIUM_TYPE = ItemTypeBuilder
            .builder(ItemWarpedNyliumStack.class)
            .vanillaItem(VanillaItemId.WARPED_NYLIUM)
            .build();
  }
}
