package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemRedstoneWireStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedstoneWireStackInitializer {
  static void init() {
    ItemTypes.REDSTONE_WIRE_TYPE = ItemTypeBuilder
            .builder(ItemRedstoneWireStack.class)
            .vanillaItem(VanillaItemId.REDSTONE_WIRE)
            .build();
  }
}
