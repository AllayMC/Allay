package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemDeepslateCoalOreStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeepslateCoalOreStackInitializer {
  static void init() {
    ItemTypes.DEEPSLATE_COAL_ORE_TYPE = ItemTypeBuilder
            .builder(ItemDeepslateCoalOreStack.class)
            .vanillaItem(VanillaItemId.DEEPSLATE_COAL_ORE)
            .build();
  }
}
