package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemStrippedWarpedHyphaeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStrippedWarpedHyphaeStackInitializer {
  static void init() {
    ItemTypes.STRIPPED_WARPED_HYPHAE_TYPE = ItemTypeBuilder
            .builder(ItemStrippedWarpedHyphaeStack.class)
            .vanillaItem(VanillaItemId.STRIPPED_WARPED_HYPHAE)
            .build();
  }
}
