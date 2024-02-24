package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemBambooStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooStairsStackInitializer {
  static void init() {
    ItemTypes.BAMBOO_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemBambooStairsStack.class)
            .vanillaItem(VanillaItemId.BAMBOO_STAIRS)
            .build();
  }
}
