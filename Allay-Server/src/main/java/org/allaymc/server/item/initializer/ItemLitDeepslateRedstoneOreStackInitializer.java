package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemLitDeepslateRedstoneOreStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLitDeepslateRedstoneOreStackInitializer {
  static void init() {
    ItemTypes.LIT_DEEPSLATE_REDSTONE_ORE_TYPE = ItemTypeBuilder
            .builder(ItemLitDeepslateRedstoneOreStack.class)
            .vanillaItem(VanillaItemId.LIT_DEEPSLATE_REDSTONE_ORE)
            .build();
  }
}
