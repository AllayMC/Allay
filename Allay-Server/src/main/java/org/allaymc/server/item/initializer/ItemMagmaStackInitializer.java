package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemMagmaStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMagmaStackInitializer {
  static void init() {
    ItemTypes.MAGMA_TYPE = ItemTypeBuilder
            .builder(ItemMagmaStack.class)
            .vanillaItem(VanillaItemId.MAGMA)
            .build();
  }
}
