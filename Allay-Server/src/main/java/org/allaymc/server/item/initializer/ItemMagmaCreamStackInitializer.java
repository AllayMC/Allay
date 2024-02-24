package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemMagmaCreamStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMagmaCreamStackInitializer {
  static void init() {
    ItemTypes.MAGMA_CREAM_TYPE = ItemTypeBuilder
            .builder(ItemMagmaCreamStack.class)
            .vanillaItem(VanillaItemId.MAGMA_CREAM)
            .build();
  }
}
