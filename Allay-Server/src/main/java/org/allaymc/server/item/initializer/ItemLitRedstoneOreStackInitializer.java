package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemLitRedstoneOreStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLitRedstoneOreStackInitializer {
  static void init() {
    ItemTypes.LIT_REDSTONE_ORE_TYPE = ItemTypeBuilder
            .builder(ItemLitRedstoneOreStack.class)
            .vanillaItem(VanillaItemId.LIT_REDSTONE_ORE)
            .build();
  }
}
