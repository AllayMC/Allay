package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSandStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSandStackInitializer {
  static void init() {
    ItemTypes.SAND_TYPE = ItemTypeBuilder
            .builder(ItemSandStack.class)
            .vanillaItem(VanillaItemId.SAND)
            .build();
  }
}
