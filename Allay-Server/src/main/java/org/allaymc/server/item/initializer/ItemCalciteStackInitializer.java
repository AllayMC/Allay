package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCalciteStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCalciteStackInitializer {
  static void init() {
    ItemTypes.CALCITE_TYPE = ItemTypeBuilder
            .builder(ItemCalciteStack.class)
            .vanillaItem(VanillaItemId.CALCITE)
            .build();
  }
}
