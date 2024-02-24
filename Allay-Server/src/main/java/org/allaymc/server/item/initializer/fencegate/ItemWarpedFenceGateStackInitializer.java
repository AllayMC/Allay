package org.allaymc.server.item.initializer.fencegate;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.fencegate.ItemWarpedFenceGateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedFenceGateStackInitializer {
  static void init() {
    ItemTypes.WARPED_FENCE_GATE_TYPE = ItemTypeBuilder
            .builder(ItemWarpedFenceGateStack.class)
            .vanillaItem(VanillaItemId.WARPED_FENCE_GATE)
            .build();
  }
}
