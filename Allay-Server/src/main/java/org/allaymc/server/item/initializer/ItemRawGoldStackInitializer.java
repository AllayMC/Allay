package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemRawGoldStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRawGoldStackInitializer {
  static void init() {
    ItemTypes.RAW_GOLD_TYPE = ItemTypeBuilder
            .builder(ItemRawGoldStack.class)
            .vanillaItem(VanillaItemId.RAW_GOLD)
            .build();
  }
}
