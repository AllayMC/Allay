package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemQuartzStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemQuartzStairsStackInitializer {
  static void init() {
    ItemTypes.QUARTZ_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemQuartzStairsStack.class)
            .vanillaItem(VanillaItemId.QUARTZ_STAIRS)
            .build();
  }
}
