package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemLitPumpkinStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLitPumpkinStackInitializer {
  static void init() {
    ItemTypes.LIT_PUMPKIN_TYPE = ItemTypeBuilder
            .builder(ItemLitPumpkinStack.class)
            .vanillaItem(VanillaItemId.LIT_PUMPKIN)
            .build();
  }
}
