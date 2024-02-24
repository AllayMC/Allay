package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCarvedPumpkinStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCarvedPumpkinStackInitializer {
  static void init() {
    ItemTypes.CARVED_PUMPKIN_TYPE = ItemTypeBuilder
            .builder(ItemCarvedPumpkinStack.class)
            .vanillaItem(VanillaItemId.CARVED_PUMPKIN)
            .build();
  }
}
