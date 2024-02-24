package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPotatoStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPotatoStackInitializer {
  static void init() {
    ItemTypes.POTATO_TYPE = ItemTypeBuilder
            .builder(ItemPotatoStack.class)
            .vanillaItem(VanillaItemId.POTATO)
            .build();
  }
}
