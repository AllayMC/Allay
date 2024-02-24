package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemWritableBookStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWritableBookStackInitializer {
  static void init() {
    ItemTypes.WRITABLE_BOOK_TYPE = ItemTypeBuilder
            .builder(ItemWritableBookStack.class)
            .vanillaItem(VanillaItemId.WRITABLE_BOOK)
            .build();
  }
}
