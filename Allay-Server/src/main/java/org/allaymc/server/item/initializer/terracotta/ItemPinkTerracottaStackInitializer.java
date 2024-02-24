package org.allaymc.server.item.initializer.terracotta;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.terracotta.ItemPinkTerracottaStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPinkTerracottaStackInitializer {
  static void init() {
    ItemTypes.PINK_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemPinkTerracottaStack.class)
            .vanillaItem(VanillaItemId.PINK_TERRACOTTA)
            .build();
  }
}
