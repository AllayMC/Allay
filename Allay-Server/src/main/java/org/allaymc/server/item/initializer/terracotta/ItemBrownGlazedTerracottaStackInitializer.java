package org.allaymc.server.item.initializer.terracotta;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.terracotta.ItemBrownGlazedTerracottaStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBrownGlazedTerracottaStackInitializer {
  static void init() {
    ItemTypes.BROWN_GLAZED_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemBrownGlazedTerracottaStack.class)
            .vanillaItem(VanillaItemId.BROWN_GLAZED_TERRACOTTA)
            .build();
  }
}
