package org.allaymc.server.item.initializer.terracotta;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.terracotta.ItemSilverGlazedTerracottaStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSilverGlazedTerracottaStackInitializer {
  static void init() {
    ItemTypes.SILVER_GLAZED_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemSilverGlazedTerracottaStack.class)
            .vanillaItem(VanillaItemId.SILVER_GLAZED_TERRACOTTA)
            .build();
  }
}
