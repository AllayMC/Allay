package org.allaymc.server.item.initializer.fencegate;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.fencegate.ItemCherryFenceGateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherryFenceGateStackInitializer {
  static void init() {
    ItemTypes.CHERRY_FENCE_GATE_TYPE = ItemTypeBuilder
            .builder(ItemCherryFenceGateStack.class)
            .vanillaItem(VanillaItemId.CHERRY_FENCE_GATE)
            .build();
  }
}
