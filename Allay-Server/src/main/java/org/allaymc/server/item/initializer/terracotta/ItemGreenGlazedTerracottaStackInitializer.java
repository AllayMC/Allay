package org.allaymc.server.item.initializer.terracotta;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.terracotta.ItemGreenGlazedTerracottaStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGreenGlazedTerracottaStackInitializer {
  static void init() {
    ItemTypes.GREEN_GLAZED_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemGreenGlazedTerracottaStack.class)
            .vanillaItem(VanillaItemId.GREEN_GLAZED_TERRACOTTA)
            .build();
  }
}
