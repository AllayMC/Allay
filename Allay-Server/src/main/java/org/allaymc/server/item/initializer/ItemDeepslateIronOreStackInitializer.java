package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemDeepslateIronOreStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeepslateIronOreStackInitializer {
  static void init() {
    ItemTypes.DEEPSLATE_IRON_ORE_TYPE = ItemTypeBuilder
            .builder(ItemDeepslateIronOreStack.class)
            .vanillaItem(VanillaItemId.DEEPSLATE_IRON_ORE)
            .build();
  }
}
