package org.allaymc.server.item.initializer.terracotta;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.terracotta.ItemRedTerracottaStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedTerracottaStackInitializer {
  static void init() {
    ItemTypes.RED_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemRedTerracottaStack.class)
            .vanillaItem(VanillaItemId.RED_TERRACOTTA)
            .build();
  }
}
