package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemDeepslateEmeraldOreStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeepslateEmeraldOreStackInitializer {
  static void init() {
    ItemTypes.DEEPSLATE_EMERALD_ORE_TYPE = ItemTypeBuilder
            .builder(ItemDeepslateEmeraldOreStack.class)
            .vanillaItem(VanillaItemId.DEEPSLATE_EMERALD_ORE)
            .build();
  }
}
