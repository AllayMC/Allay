package org.allaymc.server.item.initializer.terracotta;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.terracotta.ItemPinkGlazedTerracottaStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPinkGlazedTerracottaStackInitializer {
  static void init() {
    ItemTypes.PINK_GLAZED_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemPinkGlazedTerracottaStack.class)
            .vanillaItem(VanillaItemId.PINK_GLAZED_TERRACOTTA)
            .build();
  }
}
