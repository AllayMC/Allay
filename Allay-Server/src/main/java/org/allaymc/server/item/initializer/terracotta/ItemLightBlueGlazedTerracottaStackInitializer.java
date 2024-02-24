package org.allaymc.server.item.initializer.terracotta;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.terracotta.ItemLightBlueGlazedTerracottaStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightBlueGlazedTerracottaStackInitializer {
  static void init() {
    ItemTypes.LIGHT_BLUE_GLAZED_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemLightBlueGlazedTerracottaStack.class)
            .vanillaItem(VanillaItemId.LIGHT_BLUE_GLAZED_TERRACOTTA)
            .build();
  }
}
