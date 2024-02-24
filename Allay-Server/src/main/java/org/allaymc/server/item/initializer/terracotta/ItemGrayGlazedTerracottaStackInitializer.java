package org.allaymc.server.item.initializer.terracotta;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.terracotta.ItemGrayGlazedTerracottaStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGrayGlazedTerracottaStackInitializer {
  static void init() {
    ItemTypes.GRAY_GLAZED_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemGrayGlazedTerracottaStack.class)
            .vanillaItem(VanillaItemId.GRAY_GLAZED_TERRACOTTA)
            .build();
  }
}
