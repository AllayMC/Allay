package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemAndesiteStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAndesiteStairsStackInitializer {
  static void init() {
    ItemTypes.ANDESITE_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemAndesiteStairsStack.class)
            .vanillaItem(VanillaItemId.ANDESITE_STAIRS)
            .build();
  }
}
