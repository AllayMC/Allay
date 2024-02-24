package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemEmeraldOreStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEmeraldOreStackInitializer {
  static void init() {
    ItemTypes.EMERALD_ORE_TYPE = ItemTypeBuilder
            .builder(ItemEmeraldOreStack.class)
            .vanillaItem(VanillaItemId.EMERALD_ORE)
            .build();
  }
}
