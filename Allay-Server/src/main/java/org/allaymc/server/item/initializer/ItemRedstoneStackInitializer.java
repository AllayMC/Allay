package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemRedstoneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedstoneStackInitializer {
  static void init() {
    ItemTypes.REDSTONE_TYPE = ItemTypeBuilder
            .builder(ItemRedstoneStack.class)
            .vanillaItem(VanillaItemId.REDSTONE)
            .build();
  }
}
