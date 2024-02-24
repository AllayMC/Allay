package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemSmoothRedSandstoneStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSmoothRedSandstoneStairsStackInitializer {
  static void init() {
    ItemTypes.SMOOTH_RED_SANDSTONE_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemSmoothRedSandstoneStairsStack.class)
            .vanillaItem(VanillaItemId.SMOOTH_RED_SANDSTONE_STAIRS)
            .build();
  }
}
