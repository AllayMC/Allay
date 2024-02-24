package org.allaymc.server.item.initializer.piston;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.piston.ItemStickyPistonStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStickyPistonStackInitializer {
  static void init() {
    ItemTypes.STICKY_PISTON_TYPE = ItemTypeBuilder
            .builder(ItemStickyPistonStack.class)
            .vanillaItem(VanillaItemId.STICKY_PISTON)
            .build();
  }
}
