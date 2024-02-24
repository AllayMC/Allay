package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemDeepslateRedstoneOreStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeepslateRedstoneOreStackInitializer {
  static void init() {
    ItemTypes.DEEPSLATE_REDSTONE_ORE_TYPE = ItemTypeBuilder
            .builder(ItemDeepslateRedstoneOreStack.class)
            .vanillaItem(VanillaItemId.DEEPSLATE_REDSTONE_ORE)
            .build();
  }
}
