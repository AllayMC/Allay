package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPumpkinPieStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPumpkinPieStackInitializer {
  static void init() {
    ItemTypes.PUMPKIN_PIE_TYPE = ItemTypeBuilder
            .builder(ItemPumpkinPieStack.class)
            .vanillaItem(VanillaItemId.PUMPKIN_PIE)
            .build();
  }
}
