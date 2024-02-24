package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemItemChainStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemChainStackInitializer {
  static void init() {
    ItemTypes.ITEM_CHAIN_TYPE = ItemTypeBuilder
            .builder(ItemItemChainStack.class)
            .vanillaItem(VanillaItemId.ITEM_CHAIN)
            .build();
  }
}
