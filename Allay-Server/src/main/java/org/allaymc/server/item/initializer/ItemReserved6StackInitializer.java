package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemReserved6Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemReserved6StackInitializer {
  static void init() {
    ItemTypes.RESERVED6_TYPE = ItemTypeBuilder
            .builder(ItemReserved6Stack.class)
            .vanillaItem(VanillaItemId.RESERVED6)
            .build();
  }
}
