package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemRawGoldBlockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRawGoldBlockStackInitializer {
  static void init() {
    ItemTypes.RAW_GOLD_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemRawGoldBlockStack.class)
            .vanillaItem(VanillaItemId.RAW_GOLD_BLOCK)
            .build();
  }
}
