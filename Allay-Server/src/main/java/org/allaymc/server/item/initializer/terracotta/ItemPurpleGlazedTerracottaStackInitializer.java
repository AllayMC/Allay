package org.allaymc.server.item.initializer.terracotta;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.terracotta.ItemPurpleGlazedTerracottaStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPurpleGlazedTerracottaStackInitializer {
  static void init() {
    ItemTypes.PURPLE_GLAZED_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemPurpleGlazedTerracottaStack.class)
            .vanillaItem(VanillaItemId.PURPLE_GLAZED_TERRACOTTA)
            .build();
  }
}
