package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemBirchStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBirchStairsStackInitializer {
  static void init() {
    ItemTypes.BIRCH_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemBirchStairsStack.class)
            .vanillaItem(VanillaItemId.BIRCH_STAIRS)
            .build();
  }
}
