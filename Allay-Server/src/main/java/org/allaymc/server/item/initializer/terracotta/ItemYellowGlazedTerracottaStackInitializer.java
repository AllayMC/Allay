package org.allaymc.server.item.initializer.terracotta;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.terracotta.ItemYellowGlazedTerracottaStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemYellowGlazedTerracottaStackInitializer {
  static void init() {
    ItemTypes.YELLOW_GLAZED_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemYellowGlazedTerracottaStack.class)
            .vanillaItem(VanillaItemId.YELLOW_GLAZED_TERRACOTTA)
            .build();
  }
}
