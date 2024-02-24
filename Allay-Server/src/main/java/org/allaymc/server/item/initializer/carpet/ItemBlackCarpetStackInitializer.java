package org.allaymc.server.item.initializer.carpet;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.carpet.ItemBlackCarpetStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlackCarpetStackInitializer {
  static void init() {
    ItemTypes.BLACK_CARPET_TYPE = ItemTypeBuilder
            .builder(ItemBlackCarpetStack.class)
            .vanillaItem(VanillaItemId.BLACK_CARPET)
            .build();
  }
}
