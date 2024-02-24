package org.allaymc.server.item.initializer.fencegate;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.fencegate.ItemFenceGateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFenceGateStackInitializer {
  static void init() {
    ItemTypes.FENCE_GATE_TYPE = ItemTypeBuilder
            .builder(ItemFenceGateStack.class)
            .vanillaItem(VanillaItemId.FENCE_GATE)
            .build();
  }
}
