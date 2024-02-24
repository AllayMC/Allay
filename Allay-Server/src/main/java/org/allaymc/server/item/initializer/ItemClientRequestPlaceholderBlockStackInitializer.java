package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemClientRequestPlaceholderBlockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemClientRequestPlaceholderBlockStackInitializer {
  static void init() {
    ItemTypes.CLIENT_REQUEST_PLACEHOLDER_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemClientRequestPlaceholderBlockStack.class)
            .vanillaItem(VanillaItemId.CLIENT_REQUEST_PLACEHOLDER_BLOCK)
            .build();
  }
}
