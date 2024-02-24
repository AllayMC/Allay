package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemEnchantedBookStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEnchantedBookStackInitializer {
  static void init() {
    ItemTypes.ENCHANTED_BOOK_TYPE = ItemTypeBuilder
            .builder(ItemEnchantedBookStack.class)
            .vanillaItem(VanillaItemId.ENCHANTED_BOOK)
            .build();
  }
}
