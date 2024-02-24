package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemSpruceStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSpruceStairsStackInitializer {
  static void init() {
    ItemTypes.SPRUCE_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemSpruceStairsStack.class)
            .vanillaItem(VanillaItemId.SPRUCE_STAIRS)
            .build();
  }
}
