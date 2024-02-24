package org.allaymc.server.item.initializer.terracotta;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.terracotta.ItemYellowTerracottaStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemYellowTerracottaStackInitializer {
  static void init() {
    ItemTypes.YELLOW_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemYellowTerracottaStack.class)
            .vanillaItem(VanillaItemId.YELLOW_TERRACOTTA)
            .build();
  }
}
