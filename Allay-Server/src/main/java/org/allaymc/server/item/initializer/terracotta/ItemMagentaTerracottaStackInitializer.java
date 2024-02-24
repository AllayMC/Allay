package org.allaymc.server.item.initializer.terracotta;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.terracotta.ItemMagentaTerracottaStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMagentaTerracottaStackInitializer {
  static void init() {
    ItemTypes.MAGENTA_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemMagentaTerracottaStack.class)
            .vanillaItem(VanillaItemId.MAGENTA_TERRACOTTA)
            .build();
  }
}
