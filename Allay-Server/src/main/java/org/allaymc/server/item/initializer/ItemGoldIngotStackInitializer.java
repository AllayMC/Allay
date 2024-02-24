package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemGoldIngotStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGoldIngotStackInitializer {
  static void init() {
    ItemTypes.GOLD_INGOT_TYPE = ItemTypeBuilder
            .builder(ItemGoldIngotStack.class)
            .vanillaItem(VanillaItemId.GOLD_INGOT)
            .build();
  }
}
