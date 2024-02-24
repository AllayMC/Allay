package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPodzolStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPodzolStackInitializer {
  static void init() {
    ItemTypes.PODZOL_TYPE = ItemTypeBuilder
            .builder(ItemPodzolStack.class)
            .vanillaItem(VanillaItemId.PODZOL)
            .build();
  }
}
