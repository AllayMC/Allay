package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemRedSandstoneStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedSandstoneStairsStackInitializer {
  static void init() {
    ItemTypes.RED_SANDSTONE_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemRedSandstoneStairsStack.class)
            .vanillaItem(VanillaItemId.RED_SANDSTONE_STAIRS)
            .build();
  }
}
