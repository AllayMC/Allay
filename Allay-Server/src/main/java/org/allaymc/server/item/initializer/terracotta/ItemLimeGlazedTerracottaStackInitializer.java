package org.allaymc.server.item.initializer.terracotta;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.terracotta.ItemLimeGlazedTerracottaStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLimeGlazedTerracottaStackInitializer {
  static void init() {
    ItemTypes.LIME_GLAZED_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemLimeGlazedTerracottaStack.class)
            .vanillaItem(VanillaItemId.LIME_GLAZED_TERRACOTTA)
            .build();
  }
}
