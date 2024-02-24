package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemHardenedClayStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHardenedClayStackInitializer {
  static void init() {
    ItemTypes.HARDENED_CLAY_TYPE = ItemTypeBuilder
            .builder(ItemHardenedClayStack.class)
            .vanillaItem(VanillaItemId.HARDENED_CLAY)
            .build();
  }
}
