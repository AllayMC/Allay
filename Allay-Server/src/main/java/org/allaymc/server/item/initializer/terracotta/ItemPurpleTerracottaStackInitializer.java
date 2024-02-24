package org.allaymc.server.item.initializer.terracotta;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.terracotta.ItemPurpleTerracottaStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPurpleTerracottaStackInitializer {
  static void init() {
    ItemTypes.PURPLE_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemPurpleTerracottaStack.class)
            .vanillaItem(VanillaItemId.PURPLE_TERRACOTTA)
            .build();
  }
}
