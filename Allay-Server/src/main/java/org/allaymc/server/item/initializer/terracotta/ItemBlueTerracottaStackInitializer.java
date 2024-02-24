package org.allaymc.server.item.initializer.terracotta;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.terracotta.ItemBlueTerracottaStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlueTerracottaStackInitializer {
  static void init() {
    ItemTypes.BLUE_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemBlueTerracottaStack.class)
            .vanillaItem(VanillaItemId.BLUE_TERRACOTTA)
            .build();
  }
}
