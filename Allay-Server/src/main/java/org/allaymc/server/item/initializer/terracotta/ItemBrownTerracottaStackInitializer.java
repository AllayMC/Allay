package org.allaymc.server.item.initializer.terracotta;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.terracotta.ItemBrownTerracottaStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBrownTerracottaStackInitializer {
  static void init() {
    ItemTypes.BROWN_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemBrownTerracottaStack.class)
            .vanillaItem(VanillaItemId.BROWN_TERRACOTTA)
            .build();
  }
}
