package org.allaymc.server.item.initializer.terracotta;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.terracotta.ItemGrayTerracottaStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGrayTerracottaStackInitializer {
  static void init() {
    ItemTypes.GRAY_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemGrayTerracottaStack.class)
            .vanillaItem(VanillaItemId.GRAY_TERRACOTTA)
            .build();
  }
}
