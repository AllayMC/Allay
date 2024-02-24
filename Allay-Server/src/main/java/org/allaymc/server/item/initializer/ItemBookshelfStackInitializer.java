package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBookshelfStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBookshelfStackInitializer {
  static void init() {
    ItemTypes.BOOKSHELF_TYPE = ItemTypeBuilder
            .builder(ItemBookshelfStack.class)
            .vanillaItem(VanillaItemId.BOOKSHELF)
            .build();
  }
}
