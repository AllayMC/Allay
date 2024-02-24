package org.allaymc.server.item.initializer.terracotta;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.terracotta.ItemLightGrayTerracottaStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightGrayTerracottaStackInitializer {
  static void init() {
    ItemTypes.LIGHT_GRAY_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemLightGrayTerracottaStack.class)
            .vanillaItem(VanillaItemId.LIGHT_GRAY_TERRACOTTA)
            .build();
  }
}
