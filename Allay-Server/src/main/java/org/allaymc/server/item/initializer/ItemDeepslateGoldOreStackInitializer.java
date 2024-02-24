package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemDeepslateGoldOreStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeepslateGoldOreStackInitializer {
  static void init() {
    ItemTypes.DEEPSLATE_GOLD_ORE_TYPE = ItemTypeBuilder
            .builder(ItemDeepslateGoldOreStack.class)
            .vanillaItem(VanillaItemId.DEEPSLATE_GOLD_ORE)
            .build();
  }
}
