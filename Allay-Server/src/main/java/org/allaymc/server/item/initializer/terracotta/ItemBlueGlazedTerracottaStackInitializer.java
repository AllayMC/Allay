package org.allaymc.server.item.initializer.terracotta;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.terracotta.ItemBlueGlazedTerracottaStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlueGlazedTerracottaStackInitializer {
  static void init() {
    ItemTypes.BLUE_GLAZED_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemBlueGlazedTerracottaStack.class)
            .vanillaItem(VanillaItemId.BLUE_GLAZED_TERRACOTTA)
            .build();
  }
}
