package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemNetherWartStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetherWartStackInitializer {
  static void init() {
    ItemTypes.NETHER_WART_TYPE = ItemTypeBuilder
            .builder(ItemNetherWartStack.class)
            .vanillaItem(VanillaItemId.NETHER_WART)
            .build();
  }
}
