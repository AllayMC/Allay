package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemHopperStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHopperStackInitializer {
  static void init() {
    ItemTypes.HOPPER_TYPE = ItemTypeBuilder
            .builder(ItemHopperStack.class)
            .vanillaItem(VanillaItemId.HOPPER)
            .build();
  }
}
