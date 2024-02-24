package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemWarpedHyphaeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedHyphaeStackInitializer {
  static void init() {
    ItemTypes.WARPED_HYPHAE_TYPE = ItemTypeBuilder
            .builder(ItemWarpedHyphaeStack.class)
            .vanillaItem(VanillaItemId.WARPED_HYPHAE)
            .build();
  }
}
