package org.allaymc.server.item.initializer.terracotta;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.terracotta.ItemCyanGlazedTerracottaStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCyanGlazedTerracottaStackInitializer {
  static void init() {
    ItemTypes.CYAN_GLAZED_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemCyanGlazedTerracottaStack.class)
            .vanillaItem(VanillaItemId.CYAN_GLAZED_TERRACOTTA)
            .build();
  }
}
