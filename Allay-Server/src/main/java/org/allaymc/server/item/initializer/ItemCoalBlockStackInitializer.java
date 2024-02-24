package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCoalBlockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCoalBlockStackInitializer {
  static void init() {
    ItemTypes.COAL_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemCoalBlockStack.class)
            .vanillaItem(VanillaItemId.COAL_BLOCK)
            .build();
  }
}
