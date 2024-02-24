package org.allaymc.server.item.initializer.terracotta;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.terracotta.ItemLimeTerracottaStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLimeTerracottaStackInitializer {
  static void init() {
    ItemTypes.LIME_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemLimeTerracottaStack.class)
            .vanillaItem(VanillaItemId.LIME_TERRACOTTA)
            .build();
  }
}
