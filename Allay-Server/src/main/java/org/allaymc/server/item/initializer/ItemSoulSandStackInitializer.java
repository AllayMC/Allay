package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSoulSandStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSoulSandStackInitializer {
  static void init() {
    ItemTypes.SOUL_SAND_TYPE = ItemTypeBuilder
            .builder(ItemSoulSandStack.class)
            .vanillaItem(VanillaItemId.SOUL_SAND)
            .build();
  }
}
