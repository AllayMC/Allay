package org.allaymc.server.item.initializer.fencegate;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.fencegate.ItemMangroveFenceGateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMangroveFenceGateStackInitializer {
  static void init() {
    ItemTypes.MANGROVE_FENCE_GATE_TYPE = ItemTypeBuilder
            .builder(ItemMangroveFenceGateStack.class)
            .vanillaItem(VanillaItemId.MANGROVE_FENCE_GATE)
            .build();
  }
}
