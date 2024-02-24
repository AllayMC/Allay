package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemRedstoneOreStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedstoneOreStackInitializer {
  static void init() {
    ItemTypes.REDSTONE_ORE_TYPE = ItemTypeBuilder
            .builder(ItemRedstoneOreStack.class)
            .vanillaItem(VanillaItemId.REDSTONE_ORE)
            .build();
  }
}
