package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemItemNetherWartStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemNetherWartStackInitializer {
  static void init() {
    ItemTypes.ITEM_NETHER_WART_TYPE = ItemTypeBuilder
            .builder(ItemItemNetherWartStack.class)
            .vanillaItem(VanillaItemId.ITEM_NETHER_WART)
            .build();
  }
}
