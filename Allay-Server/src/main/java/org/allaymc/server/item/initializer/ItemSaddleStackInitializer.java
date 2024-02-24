package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSaddleStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSaddleStackInitializer {
  static void init() {
    ItemTypes.SADDLE_TYPE = ItemTypeBuilder
            .builder(ItemSaddleStack.class)
            .vanillaItem(VanillaItemId.SADDLE)
            .build();
  }
}
