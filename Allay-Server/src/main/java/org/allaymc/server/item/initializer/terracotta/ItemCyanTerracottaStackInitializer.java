package org.allaymc.server.item.initializer.terracotta;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.terracotta.ItemCyanTerracottaStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCyanTerracottaStackInitializer {
  static void init() {
    ItemTypes.CYAN_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemCyanTerracottaStack.class)
            .vanillaItem(VanillaItemId.CYAN_TERRACOTTA)
            .build();
  }
}
