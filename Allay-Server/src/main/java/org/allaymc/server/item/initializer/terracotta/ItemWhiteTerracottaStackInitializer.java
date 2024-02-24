package org.allaymc.server.item.initializer.terracotta;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.terracotta.ItemWhiteTerracottaStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWhiteTerracottaStackInitializer {
  static void init() {
    ItemTypes.WHITE_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemWhiteTerracottaStack.class)
            .vanillaItem(VanillaItemId.WHITE_TERRACOTTA)
            .build();
  }
}
