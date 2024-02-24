package org.allaymc.server.item.initializer.terracotta;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.terracotta.ItemRedGlazedTerracottaStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedGlazedTerracottaStackInitializer {
  static void init() {
    ItemTypes.RED_GLAZED_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemRedGlazedTerracottaStack.class)
            .vanillaItem(VanillaItemId.RED_GLAZED_TERRACOTTA)
            .build();
  }
}
