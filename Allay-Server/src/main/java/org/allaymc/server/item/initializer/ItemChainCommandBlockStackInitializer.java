package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemChainCommandBlockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChainCommandBlockStackInitializer {
  static void init() {
    ItemTypes.CHAIN_COMMAND_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemChainCommandBlockStack.class)
            .vanillaItem(VanillaItemId.CHAIN_COMMAND_BLOCK)
            .build();
  }
}
