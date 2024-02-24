package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemChainStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChainStackInitializer {
  static void init() {
    ItemTypes.CHAIN_TYPE = ItemTypeBuilder
            .builder(ItemChainStack.class)
            .vanillaItem(VanillaItemId.CHAIN)
            .build();
  }
}
