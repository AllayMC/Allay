package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemStainedHardenedClayStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStainedHardenedClayStackInitializer {
  static void init() {
    ItemTypes.STAINED_HARDENED_CLAY_TYPE = ItemTypeBuilder
            .builder(ItemStainedHardenedClayStack.class)
            .vanillaItem(VanillaItemId.STAINED_HARDENED_CLAY)
            .build();
  }
}
