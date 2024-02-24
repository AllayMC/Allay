package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemDispenserStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDispenserStackInitializer {
  static void init() {
    ItemTypes.DISPENSER_TYPE = ItemTypeBuilder
            .builder(ItemDispenserStack.class)
            .vanillaItem(VanillaItemId.DISPENSER)
            .build();
  }
}
