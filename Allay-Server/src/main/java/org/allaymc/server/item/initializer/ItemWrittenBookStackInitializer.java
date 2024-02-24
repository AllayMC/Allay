package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemWrittenBookStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWrittenBookStackInitializer {
  static void init() {
    ItemTypes.WRITTEN_BOOK_TYPE = ItemTypeBuilder
            .builder(ItemWrittenBookStack.class)
            .vanillaItem(VanillaItemId.WRITTEN_BOOK)
            .build();
  }
}
