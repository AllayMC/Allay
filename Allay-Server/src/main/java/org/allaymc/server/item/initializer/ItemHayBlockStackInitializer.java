package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemHayBlockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHayBlockStackInitializer {
  static void init() {
    ItemTypes.HAY_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemHayBlockStack.class)
            .vanillaItem(VanillaItemId.HAY_BLOCK)
            .build();
  }
}
