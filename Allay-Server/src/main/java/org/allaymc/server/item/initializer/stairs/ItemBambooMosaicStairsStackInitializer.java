package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemBambooMosaicStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooMosaicStairsStackInitializer {
  static void init() {
    ItemTypes.BAMBOO_MOSAIC_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemBambooMosaicStairsStack.class)
            .vanillaItem(VanillaItemId.BAMBOO_MOSAIC_STAIRS)
            .build();
  }
}
