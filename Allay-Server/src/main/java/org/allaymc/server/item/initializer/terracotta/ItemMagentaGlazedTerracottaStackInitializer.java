package org.allaymc.server.item.initializer.terracotta;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.terracotta.ItemMagentaGlazedTerracottaStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMagentaGlazedTerracottaStackInitializer {
  static void init() {
    ItemTypes.MAGENTA_GLAZED_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemMagentaGlazedTerracottaStack.class)
            .vanillaItem(VanillaItemId.MAGENTA_GLAZED_TERRACOTTA)
            .build();
  }
}
