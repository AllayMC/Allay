package org.allaymc.server.item.initializer.piston;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.piston.ItemPistonStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPistonStackInitializer {
  static void init() {
    ItemTypes.PISTON_TYPE = ItemTypeBuilder
            .builder(ItemPistonStack.class)
            .vanillaItem(VanillaItemId.PISTON)
            .build();
  }
}
