package org.allaymc.server.item.initializer.terracotta;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.terracotta.ItemWhiteGlazedTerracottaStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWhiteGlazedTerracottaStackInitializer {
  static void init() {
    ItemTypes.WHITE_GLAZED_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemWhiteGlazedTerracottaStack.class)
            .vanillaItem(VanillaItemId.WHITE_GLAZED_TERRACOTTA)
            .build();
  }
}
