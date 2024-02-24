package org.allaymc.server.item.initializer.terracotta;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.terracotta.ItemLightBlueTerracottaStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightBlueTerracottaStackInitializer {
  static void init() {
    ItemTypes.LIGHT_BLUE_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemLightBlueTerracottaStack.class)
            .vanillaItem(VanillaItemId.LIGHT_BLUE_TERRACOTTA)
            .build();
  }
}
