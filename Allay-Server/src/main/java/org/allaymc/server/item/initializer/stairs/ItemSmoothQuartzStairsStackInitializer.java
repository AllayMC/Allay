package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemSmoothQuartzStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSmoothQuartzStairsStackInitializer {
  static void init() {
    ItemTypes.SMOOTH_QUARTZ_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemSmoothQuartzStairsStack.class)
            .vanillaItem(VanillaItemId.SMOOTH_QUARTZ_STAIRS)
            .build();
  }
}
