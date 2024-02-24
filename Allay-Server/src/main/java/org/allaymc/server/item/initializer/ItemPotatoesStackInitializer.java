package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPotatoesStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPotatoesStackInitializer {
  static void init() {
    ItemTypes.POTATOES_TYPE = ItemTypeBuilder
            .builder(ItemPotatoesStack.class)
            .vanillaItem(VanillaItemId.POTATOES)
            .build();
  }
}
