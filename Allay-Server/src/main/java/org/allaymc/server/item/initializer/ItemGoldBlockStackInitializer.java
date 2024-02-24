package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemGoldBlockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGoldBlockStackInitializer {
  static void init() {
    ItemTypes.GOLD_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemGoldBlockStack.class)
            .vanillaItem(VanillaItemId.GOLD_BLOCK)
            .build();
  }
}
