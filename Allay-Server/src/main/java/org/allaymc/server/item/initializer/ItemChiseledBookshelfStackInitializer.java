package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemChiseledBookshelfStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChiseledBookshelfStackInitializer {
  static void init() {
    ItemTypes.CHISELED_BOOKSHELF_TYPE = ItemTypeBuilder
            .builder(ItemChiseledBookshelfStack.class)
            .vanillaItem(VanillaItemId.CHISELED_BOOKSHELF)
            .build();
  }
}
