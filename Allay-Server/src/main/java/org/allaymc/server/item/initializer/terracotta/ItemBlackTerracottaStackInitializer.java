package org.allaymc.server.item.initializer.terracotta;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.terracotta.ItemBlackTerracottaStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlackTerracottaStackInitializer {
  static void init() {
    ItemTypes.BLACK_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemBlackTerracottaStack.class)
            .vanillaItem(VanillaItemId.BLACK_TERRACOTTA)
            .build();
  }
}
