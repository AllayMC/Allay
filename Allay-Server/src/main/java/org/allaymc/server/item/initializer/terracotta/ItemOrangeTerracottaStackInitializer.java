package org.allaymc.server.item.initializer.terracotta;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.terracotta.ItemOrangeTerracottaStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOrangeTerracottaStackInitializer {
  static void init() {
    ItemTypes.ORANGE_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemOrangeTerracottaStack.class)
            .vanillaItem(VanillaItemId.ORANGE_TERRACOTTA)
            .build();
  }
}
