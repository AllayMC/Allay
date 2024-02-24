package org.allaymc.server.item.initializer.carpet;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.carpet.ItemCyanCarpetStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCyanCarpetStackInitializer {
  static void init() {
    ItemTypes.CYAN_CARPET_TYPE = ItemTypeBuilder
            .builder(ItemCyanCarpetStack.class)
            .vanillaItem(VanillaItemId.CYAN_CARPET)
            .build();
  }
}
