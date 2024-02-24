package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPumpkinStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPumpkinStackInitializer {
  static void init() {
    ItemTypes.PUMPKIN_TYPE = ItemTypeBuilder
            .builder(ItemPumpkinStack.class)
            .vanillaItem(VanillaItemId.PUMPKIN)
            .build();
  }
}
