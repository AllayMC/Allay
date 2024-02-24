package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemMovingBlockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMovingBlockStackInitializer {
  static void init() {
    ItemTypes.MOVING_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemMovingBlockStack.class)
            .vanillaItem(VanillaItemId.MOVING_BLOCK)
            .build();
  }
}
