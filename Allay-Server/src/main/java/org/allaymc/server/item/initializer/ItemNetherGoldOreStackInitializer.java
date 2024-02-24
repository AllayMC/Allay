package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemNetherGoldOreStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetherGoldOreStackInitializer {
  static void init() {
    ItemTypes.NETHER_GOLD_ORE_TYPE = ItemTypeBuilder
            .builder(ItemNetherGoldOreStack.class)
            .vanillaItem(VanillaItemId.NETHER_GOLD_ORE)
            .build();
  }
}
