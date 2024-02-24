package org.allaymc.server.item.initializer.carpet;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.carpet.ItemWhiteCarpetStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWhiteCarpetStackInitializer {
  static void init() {
    ItemTypes.WHITE_CARPET_TYPE = ItemTypeBuilder
            .builder(ItemWhiteCarpetStack.class)
            .vanillaItem(VanillaItemId.WHITE_CARPET)
            .build();
  }
}
