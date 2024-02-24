package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemSmoothSandstoneStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSmoothSandstoneStairsStackInitializer {
  static void init() {
    ItemTypes.SMOOTH_SANDSTONE_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemSmoothSandstoneStairsStack.class)
            .vanillaItem(VanillaItemId.SMOOTH_SANDSTONE_STAIRS)
            .build();
  }
}
