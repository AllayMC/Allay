package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemEmeraldStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEmeraldStackInitializer {
  static void init() {
    ItemTypes.EMERALD_TYPE = ItemTypeBuilder
            .builder(ItemEmeraldStack.class)
            .vanillaItem(VanillaItemId.EMERALD)
            .build();
  }
}
