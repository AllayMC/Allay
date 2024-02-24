package org.allaymc.server.item.initializer.fencegate;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.fencegate.ItemJungleFenceGateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemJungleFenceGateStackInitializer {
  static void init() {
    ItemTypes.JUNGLE_FENCE_GATE_TYPE = ItemTypeBuilder
            .builder(ItemJungleFenceGateStack.class)
            .vanillaItem(VanillaItemId.JUNGLE_FENCE_GATE)
            .build();
  }
}
