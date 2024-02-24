package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemNetherWartBlockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetherWartBlockStackInitializer {
  static void init() {
    ItemTypes.NETHER_WART_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemNetherWartBlockStack.class)
            .vanillaItem(VanillaItemId.NETHER_WART_BLOCK)
            .build();
  }
}
