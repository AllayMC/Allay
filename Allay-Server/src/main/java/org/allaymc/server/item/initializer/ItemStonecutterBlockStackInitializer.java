package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemStonecutterBlockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStonecutterBlockStackInitializer {
  static void init() {
    ItemTypes.STONECUTTER_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemStonecutterBlockStack.class)
            .vanillaItem(VanillaItemId.STONECUTTER_BLOCK)
            .build();
  }
}
