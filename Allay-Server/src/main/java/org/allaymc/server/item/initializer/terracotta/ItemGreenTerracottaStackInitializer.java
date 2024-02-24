package org.allaymc.server.item.initializer.terracotta;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.terracotta.ItemGreenTerracottaStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGreenTerracottaStackInitializer {
  static void init() {
    ItemTypes.GREEN_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemGreenTerracottaStack.class)
            .vanillaItem(VanillaItemId.GREEN_TERRACOTTA)
            .build();
  }
}
