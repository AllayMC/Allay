package org.allaymc.server.item.initializer.terracotta;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.terracotta.ItemBlackGlazedTerracottaStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlackGlazedTerracottaStackInitializer {
  static void init() {
    ItemTypes.BLACK_GLAZED_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemBlackGlazedTerracottaStack.class)
            .vanillaItem(VanillaItemId.BLACK_GLAZED_TERRACOTTA)
            .build();
  }
}
