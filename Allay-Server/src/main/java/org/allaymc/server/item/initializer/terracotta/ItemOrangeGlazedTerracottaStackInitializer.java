package org.allaymc.server.item.initializer.terracotta;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.terracotta.ItemOrangeGlazedTerracottaStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOrangeGlazedTerracottaStackInitializer {
  static void init() {
    ItemTypes.ORANGE_GLAZED_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemOrangeGlazedTerracottaStack.class)
            .vanillaItem(VanillaItemId.ORANGE_GLAZED_TERRACOTTA)
            .build();
  }
}
